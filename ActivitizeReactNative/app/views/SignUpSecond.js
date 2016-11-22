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
  TouchableHighlight,
  Navigator,
  TouchableOpacity,
  Alert,
  DatePickerAndroid,
  TouchableWithoutFeedback
} from 'react-native';

var background = require('../../img/login5-1.jpg');

export class SignUpSecond extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      firstName: '',
      lastName: '',
      nickname: '',
      dob: '',
      phone: '',
      dateText: 'Birthday'
    };
  }

  render() {
    return (
      <Navigator
          renderScene={this.renderScene.bind(this)}
      />
    );
  }
  renderScene(route, navigator) {
    return (
        <View style={styles.container}>
          <Image style={styles.bg} source={background} />
            <View style={styles.header}>
                <Image style={styles.mark} source={require('../../img/logo2.png')} />
            </View>
            <View style={styles.inputs}>
                <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input, styles.whiteFont]}
                        placeholder="First Name"
                        placeholderTextColor="#FFF"
                        onChangeText={(firstName) => this.setState({firstName})}
                        value={this.state.firstName}
                    />
                </View>
                <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input, styles.whiteFont]}
                        placeholder="Nickname"
                        placeholderTextColor="#FFF"
                        onChangeText={(nickname) => this.setState({nickname})}
                        value={this.state.nickname}
                    />
                </View>
                <View style={styles.inputContainer}>
                    <TextInput
                        style={[styles.input, styles.whiteFont]}
                        placeholder="Last Name"
                        placeholderTextColor="#FFF"
                        onChangeText={(lastName) => this.setState({lastName})}
                        value={this.state.lastName}
                    />
                </View>
                <TouchableWithoutFeedback
                    onPress={this.showPicker.bind(this, {date: new Date(1990, 0, 1)})}>
                    <View style={styles.inputContainer}>
                    <Text style={styles.date}>{this.state.dateText}</Text>
                    </View>
                </TouchableWithoutFeedback>
                <View style={styles.inputContainer}>
                    <TextInput
                        style={[styles.input, styles.whiteFont]}
                        placeholder="Phone Number (Optional)"
                        placeholderTextColor="#FFF"
                        onChangeText={(phone) => this.setState({phone})}
                        value={this.state.phone}
                    />
                </View>
            </View>
              <TouchableHighlight style={styles.signin} underlayColor='#840032' onPress={this.gotoNext.bind(this)}>
                <Text style={styles.whiteFont}>Sign up</Text>
              </TouchableHighlight>
              <TouchableHighlight style={styles.back} underlayColor='#840032' onPress={this.back.bind(this)}>
                <Text style={styles.whiteFont}>Go back</Text>
              </TouchableHighlight>
        </View>
    );
  }

  showPicker = async (options) => {
    try {
      const {action, year, month, day} = await DatePickerAndroid.open(options);
      if (action === DatePickerAndroid.dismissedAction) {
      } else {
        var dateString = (month + 1) + "/" + day + "/" + year;
        var dbDate = day + "/" + (month + 1) + "/" + year
        this.setState({dateText: dateString});
        this.setState({dob: dbDate});
        console.log("Birthday: " + this.state.dob)
      }
    } catch ({code, message}) {
      console.warn('Error ', message);
    }
  };
  gotoNext() {
      let navigator = this.props.navigator;

      if (!this.state.firstName.trim()) {
        Alert.alert("First name is a required field.")
        return;
      }
      else if (!this.state.lastName.trim()) {
        Alert.alert("Last name is a required field.")
        return;
      }
      else if (!this.state.nickname.trim()) {
        Alert.alert("Nickname is a required field.")
        return;
      }
      else if (!this.state.dob.trim()) {
        Alert.alert("Birthday is a required field.")
        return;
      }

      var params = {
          username: this.props.navigator.state.username,
          password: this.props.navigator.state.password,
          nickname: this.state.nickname,
          firstName: this.state.firstName,
          lastName: this.state.lastName,
          age: this.state.dob,
          email: this.props.navigator.state.email,
          phoneNumber: this.state.phone,
          numberOfFriends: '0',
          usingFacebook: 'false'
        };
      console.log("json: " + JSON.stringify(params));
    fetch('https://activitize.net/activitize/user/createUser', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(params)
    })
    .then(function(response) {
      return response.json();
    })
    .then(function(json) {
      console.log("response: " + json.responseStatus);
      console.log("error: " + json.errorMessage);
      if (json.responseStatus === 'OK') {
        Alert.alert("Create user success");
        navigator.push({
          id: 'EventFeed',
          name: 'Events',
        });
      } else {
        Alert.alert(json.errorMessage);
      }
    })
    .catch((error) => {
        console.error(error);
      });
      // this.props.navigator.push({
      //   id: 'EventFeed',
      //   name: 'Events',
      // });
  }

  back() {
    this.props.navigator.pop();
  }
}

var NavigationBarRouteMapper = {
  LeftButton(route, navigator, index, navState) {
    return null;
  },
  RightButton(route, navigator, index, navState) {
    return null;
  },
  Title(route, navigator, index, navState) {
    return (
      <TouchableOpacity style={{flex: 1, justifyContent: 'center'}}>
        <Text style={{color: 'white', margin: 10, fontSize: 16}}>
          Login
        </Text>
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
    bg: {
        position: 'absolute',
        left: 0,
        top: 0,
        width: windowSize.width,
        height: windowSize.height
    },
    header: {
        justifyContent: 'center',
        alignItems: 'center',
        flex: .5,
        backgroundColor: 'transparent'
    },
    mark: {
        width: 250,
        height: 150
    },
    signin: {
        backgroundColor: '#E07E06',
        padding: 20,
        alignItems: 'center',
        marginTop: 40
    },
    signup: {
      justifyContent: 'center',
      alignItems: 'center',
      flex: .15
    },
    inputs: {
        marginBottom: 100,
        flex: .25,
        justifyContent: 'space-between',
    },
    inputPassword: {
        marginLeft: 15,
        width: 20,
        height: 21
    },
    inputUsername: {
      marginLeft: 15,
      width: 20,
      height: 20
    },
    inputContainer: {
        padding: 20,
        borderWidth: 1,
        borderBottomColor: '#FFF',
        borderColor: 'transparent'
    },
    input: {
        position: 'absolute',
        left: 61,
        top: 12,
        right: 0,
        height: 40,
        fontSize: 12
    },
    forgotContainer: {
      alignItems: 'flex-end',
      padding: 15,
    },
    greyFont: {
      color: '#D8D8D8'
    },
    whiteFont: {
      color: '#FFF'
    },
    back: {
        backgroundColor: '#2B4162',
        padding: 20,
        alignItems: 'center'
    },
    date: {
        position: 'absolute',
        left: 61,
        top: 12,
        right: 0,
        height: 40,
        fontSize: 12,
        marginRight: 50,
        color: '#FFF'
    }
});