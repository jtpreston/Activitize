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

export class Profile extends React.Component{
  constructor(props) {
    super(props);
    this.state = {};
  }
  
  async getUserInfo() {
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
          console.error(error);
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
        <View style={styles.textContainer}>
          <Text style={styles.label}First Name />
          <Text style={styles.name}>{this.props.navigator.state.first}</Text>
          <Text style={styles.label}Last Name />
          <Text style={styles.name}>{this.props.navigator.state.last}</Text>
          <Text style={styles.label}Nickname />
          <Text style={styles.date}>{this.props.navigator.state.nickname}</Text>
          <Text style={styles.label}Date of Birth />
	  <Text style={styles.time}>{this.props.navigator.state.dobString}</Text>
          <Text style={styles.label}Phone Number />
	  <Text style={styles.time}>{this.props.navigator.state.phone}</Text>
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
  }
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
          onPress={() => navigator.parentNavigator.pop()}>
        <Text style={{color: 'white', margin: 10,}}>
          Done
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
    textContainer: {
      width: windowSize.width
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
    date: {
      color: '#1A1423',
      fontSize: 20,
      marginTop: 10
    },
    time: {
      color: '#1A1423',
      fontSize: 16,
      marginTop: 10 
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
