'use strict';
var Dimensions = require('Dimensions');
var windowSize = Dimensions.get('window');

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  View,
  Text,
  TextInput,
  Image,
  Navigator,
  TouchableOpacity,
  TouchableHighlight,
  AsyncStorage,
  TouchableWithoutFeedback,
  DatePickerAndroid,
  Alert
} from 'react-native';

var CookieManager = require('react-native-cookies');

function formatAgeString(age) {
  var year = age.substring(0, age.indexOf(','));
  var month = age.substring(age.indexOf(',') + 1, age.lastIndexOf(','));
  var day = age.substring(age.lastIndexOf(',') + 1, age.length);
  return month + '/' + day + '/' + year;
}

function getDateFromString(age) {
  var month = parseInt(age.substring(0, age.indexOf('/')));
  var day = parseInt(age.substring(age.indexOf('/') + 1, age.lastIndexOf('/')));
  var year = parseInt(age.substring(age.lastIndexOf('/') + 1, age.length));
  var date = new Date();
  date.setMonth(month - 1);
  date.setDate(day);
  date.setYear(year);
  return date;
}

function makeDBAge(age) {
  var month = age.substring(0, age.indexOf('/'));
  var day = age.substring(age.indexOf('/') + 1, age.lastIndexOf('/'));
  var year = age.substring(age.lastIndexOf('/') + 1, age.length);
  return day + '/' + month + '/' + year;
}

export class EditProfile extends React.Component{
  constructor(props) {
    super(props);
    this.cancelDeleteUser = this.cancelDeleteUser.bind(this);
    this.proceedDeleteUser = this.proceedDeleteUser.bind(this);
    this.state = {};
  }

  async clearStorage() {
    await AsyncStorage.removeItem('xcsrfToken');
    await AsyncStorage.removeItem('jsessionid');
    await AsyncStorage.removeItem('remember');
  }

  deleteUser() {
    Alert.alert(
      'Delete user?',
      'Are you sure you want to delete your user?',
      [
      {text: 'No', onPress: () => this.cancelDeleteUser(), style: 'cancel'},
      {text: 'Yes', onPress: () => this.proceedDeleteUser()}
      ]
      )
  }

  cancelDeleteUser() {
    console.log("no pressed")
  }

  async proceedDeleteUser() {
    console.log("yes pressed")
    var navigator = this.props.navigator;
    var self = this;
    var url = 'https://activitize.net/activitize/user/deleteUser';
    var cookie = await AsyncStorage.getItem('jsessionid');
    var token = await AsyncStorage.getItem('xcsrfToken');
    var headers = {
      Cookie: cookie,
      'X-CSRF-TOKEN': token
    }

    fetch (url, {
      method: 'GET',
      headers: headers,
    })
    .then(async function(response) {
      console.log("status: " + response.status)
      console.log("xcsrftoken: " + response.headers.get('X-CSRF-TOKEN'));
      console.log("setting xcsrfToken")
      await AsyncStorage.setItem('xcsrfToken', response.headers.get('X-CSRF-TOKEN'));

      var token = await AsyncStorage.getItem('xcsrfToken');

      var headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        Cookie: cookie,
        'X-CSRF-TOKEN': token
      }

    // console.log("headers: " + JSON.stringify(headers))

      fetch(url, {
        method: 'POST',
        headers: headers
      })
      .then(function(response) {
        console.log("status: " + response.status)
        return response.json()
      })
      .then(async function(json) {
        console.log("response status: " + json.responseStatus)
        if (json.responseStatus === "OK") {
          await self.clearStorage();
          navigator.popToTop();
        } else {
          Alert.alert(json.errorMessage);
        }
      })
      .catch((error) => {
        Alert.alert("There was an error deleting your user")
        console.log(error);
      });
    })
    .catch((error) => {
      Alert.alert("There was an error deleting your user")
      console.log(error);
    });
  }

  async componentWillMount() {
    var self = this;
    var url = 'https://activitize.net/activitize/user/getUserInfo';
    var cookie = await AsyncStorage.getItem('jsessionid');
    var token = await AsyncStorage.getItem('xcsrfToken');
    // console.log("token: " + token)
    var headers = {
      'Accept': 'application/json',
      Cookie: cookie,
      'X-CSRF-TOKEN': token
    }

    // console.log("headers: " + JSON.stringify(headers))

    fetch(url, {
          method: 'GET',
          headers: headers
        })
        .then(async function(response) {
          console.log("status: " + response.status)
          // console.log("xcsrftoken: " + response.headers.get('X-CSRF-TOKEN'));
          await AsyncStorage.setItem('xcsrfToken', response.headers.get('X-CSRF-TOKEN'));
          return response.json()
        })
        .then(function(json) {
          var age = json.age.toString();
          console.log("age: " + age);
          age = formatAgeString(age);
          console.log("age: " + age);
          var dob = getDateFromString(age);
          var firstName = json.firstName.toString();
          var lastName = json.lastName.toString();
          var nickname = json.nickname.toString();
          var phone = json.phoneNumber.toString();
          console.log("first: " + firstName);
          console.log("last: " + lastName);
          console.log("nickname: " + nickname);
          console.log("phone: " + phone);
          self.props.navigator.setState({
            dob: dob,
            dobString: age,
            first: firstName,
            last: lastName,
            nickname: nickname,
            phone: phone
          });
        })
        .catch((error) => {
          Alert.alert("There was an error loading profile information.")
          console.error(error);
          self.props.navigator.pop();
        });
  }

  render() {
    return (
      <Navigator
          renderScene={this.renderScene.bind(this)}
          navigator={this.props.navigator}
          navigationBar={
            <Navigator.NavigationBar style={{backgroundColor: '#E07E06'}}
                routeMapper={NavigationBarRouteMapper} />
          } />
    );
  }
  renderScene(route, navigator) {
    return (
      <View style={styles.container}>
        <View style={styles.inputs}>
        <View>
        <Text style={styles.label}>First Name:</Text>
        </View>
          <View style={styles.inputContainer}>
            <TextInput 
            style={[styles.input]}
            onChangeText={(first) => this.props.navigator.setState({first})}
            value={this.props.navigator.state.first}
            />
            </View>
            <View>
            <Text style={styles.label}>Nickname:</Text>
            </View>
            <View style={styles.inputContainer}>
            <TextInput 
            style={[styles.input]}
            onChangeText={(nickname) => this.props.navigator.setState({nickname})}
            value={this.props.navigator.state.nickname}
            />
            </View>
            <View>
            <Text style={styles.label}>Last Name:</Text>
            </View>
            <View style={styles.inputContainer}>
            <TextInput 
            style={[styles.input]}
            onChangeText={(last) => this.props.navigator.setState({last})}
            value={this.props.navigator.state.last}
            />
          </View>
          <View>
          <Text style={styles.label}>Date of Birth:</Text>
          </View>
          <TouchableWithoutFeedback
            onPress={this.showPicker.bind(this, {date: this.props.navigator.state.dob})}>
            <View style={styles.inputContainer}>
            <Text style={styles.input}>{this.props.navigator.state.dobString}</Text>
            </View>
          </TouchableWithoutFeedback>
          <TouchableHighlight style={styles.signin} underlayColor='#840032' onPress={this.deleteUser.bind(this)}>
                <Text style={styles.whiteFont}>Delete user</Text>
              </TouchableHighlight>
        </View>
      </View>
      );
  }

  showPicker = async (options) => {
    try {
      const {action, year, month, day} = await DatePickerAndroid.open(options);
      if (action === DatePickerAndroid.dismissedAction) {
      } else {
        var date = new Date();
        date.setMonth(month);
        date.setDate(day);
        date.setYear(year);
        this.props.navigator.setState({dob: date});
        var dateString = (month + 1) + "/" + day + "/" + year;
        this.props.navigator.setState({dobString: dateString});
      }
    } catch ({code, message}) {
      console.warn('Error ', message);
    }
  };
}

var NavigationBarRouteMapper = {
  LeftButton(route, navigator, index, navState) {
    return (
      <TouchableOpacity style={{flex: 1, justifyContent: 'center'}}
          onPress={() => navigator.parentNavigator.pop()}>
        <Text style={{color: 'white', margin: 10,}}>
          Back
        </Text>
      </TouchableOpacity>
    );
  },
  RightButton(route, navigator, index, navState) {
    return (
      <TouchableOpacity style={{flex: 1, justifyContent: 'center'}}
      onPress={async () => {
        var nav = navigator.parentNavigator;
        var url = 'https://activitize.net/activitize/user/editUser';
        var cookie = await AsyncStorage.getItem('jsessionid');
        var token = await AsyncStorage.getItem('xcsrfToken');
        
        var headers = {
          Cookie: cookie,
          'X-CSRF-TOKEN': token
        }

        fetch (url, {
          method: 'GET',
          headers: headers,
        })
        .then(async function(response) {
            console.log("status: " + response.status)
            console.log("xcsrftoken: " + response.headers.get('X-CSRF-TOKEN'));
            console.log("setting xcsrfToken")
            await AsyncStorage.setItem('xcsrfToken', response.headers.get('X-CSRF-TOKEN'));

          var token = await AsyncStorage.getItem('xcsrfToken');

          var headers = {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          Cookie: cookie,
          'X-CSRF-TOKEN': token
        }

        var params = {
          firstName: nav.state.first,
          lastName: nav.state.last,
          nickname: nav.state.nickname,
          age: makeDBAge(nav.state.dobString),
          phoneNumber: nav.state.phone
        }

        // console.log("headers: " + JSON.stringify(headers))
        console.log("params: " + JSON.stringify(params))

        fetch(url, {
          method: 'POST',
          headers: headers,
          body: JSON.stringify(params)
        })
        .then(async function(response) {
          console.log("status: " + response.status)
          // console.log("xcsrftoken: " + response.headers.get('X-CSRF-TOKEN'));
          if (response.ok) {
            console.log("setting xcsrfToken")
            await AsyncStorage.setItem('xcsrfToken', response.headers.get('X-CSRF-TOKEN'));
          }

          return response.json();
        })
        .then(function(json) {
          console.log("json.responseStatus: " + json.responseStatus);
          if (json.responseStatus === "OK") {
            nav.pop();
          } else {
            Alert.alert(json.errorMessage);
          }
        })
        .catch((error) => {
          Alert.alert("There was an error submitting your changes")
          console.error(error);
        });
      })
      .catch((error) => {
        Alert.alert("There was an error submitting your changes")
        console.error(error);
      });
          }}>
          <Text style={{color: 'white', margin: 10,}}>
          Submit
          </Text>
          </TouchableOpacity>
          );
  },
  Title(route, navigator, index, navState) {
    return (
      <TouchableOpacity style={{flex: 1, justifyContent: 'center'}}>
      </TouchableOpacity>
    );
  }
};

var styles = StyleSheet.create({
    container: {
      flexDirection: 'column',
      flex: 1,
      backgroundColor: 'transparent'
    },
    greyFont: {
      color: '#D8D8D8'
    },
    whiteFont: {
      color: '#FFF'
    },
    header: {
      marginTop: 100,
      backgroundColor: 'transparent'
    },
    mark: {
      width: 250,
      height: 250
    },
    display: {
      color: '#FFF', 
      fontSize: 16,
      textAlign: 'center'
    },
    info_item: {
        alignItems: 'center',
        justifyContent: 'center',
        flexDirection: 'row'
    },
    profile_info: {
        marginTop: 20,
        alignItems: 'center',
        justifyContent: 'center',
        flexDirection: 'row'
    },
    name: {
      color: '#547980', 
      fontSize: 24
    },
    button: {
        backgroundColor: '#547980',
        padding: 10,
        marginLeft: 10,
        marginRight: 10,
        width: 100
    },
    inputs: {
        marginTop: 100,
        marginBottom: 10,
        flex: .25
    },
    inputContainer: {
        padding: 30,
        borderWidth: 1,
        marginTop: 10,
        borderColor: 'transparent'
    },
    input: {
        position: 'absolute',
        left: 61,
        top: 12,
        right: 0,
        height: 40,
        fontSize: 20,
        color: '#1A1423',
    },
    label: {
        position: 'absolute',
        left: 61,
        top: 12,
        right: 0,
        height: 40,
        fontSize: 10,
        color: '#1A1423',
    },
    signin: {
        backgroundColor: '#B20000',
        padding: 20,
        position: 'absolute',
        bottom: 0,
        left: 0,
        right: 0,
        alignItems: 'center'
    }
});