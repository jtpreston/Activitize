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
  Alert
} from 'react-native';

const FBSDK = require('react-native-fbsdk');
const {
  LoginManager,
} = FBSDK;

var background = require('../../img/login5-1.jpg');

function isValidUsername(string) {
  var re = new RegExp("[A-Za-z0-9\u00C0-\u1FFF\u2C00-\uD7FF\w]{6,}");
  return re.test(string);
}

function isValidPassword(string) {
  var allCharsTest = new RegExp("([a-z]|[A-Z]|[!@#$?]|[0-9]|[\u00C0-\u1FFF\u2C00-\uD7FF\w]){8,}");
  var hasAllValidChars = allCharsTest.test(string);
  var specialCharsTest = new RegExp("[!@#$?]");
  var hasSpecialChar = specialCharsTest.test(string);
  var uppercaseTest = new RegExp("[A-Z]");
  var hasUppercase = uppercaseTest.test(string);
  var numericTest = new RegExp("[0-9]");
  var hasNumeric = numericTest.test(string);
  var lowercaseTest = new RegExp("[a-z]");
  var hasLowercase = lowercaseTest.test(string);
  return hasAllValidChars && hasSpecialChar && hasUppercase && hasNumeric && hasLowercase;
}

function isPasswordMatch(str1, str2) {
  return str1 == str2;
}

function isValidEmail(string) {
  var emailTest = new RegExp("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}");
  return emailTest.test(string);
}

export class SignUp extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      verifyPassword: '',
      fbAccessToken: '',
      email: ''
    };
  }

  login() {
    var view = this;
    LoginManager.logInWithReadPermissions(['public_profile', 'email', 'user_friends']).then(
      function(result) {
        if (result.isCancelled) {
          Alert.alert('Login was cancelled');
      } else {
          Alert.alert('Login was successful with permissions: '
        + result.grantedPermissions.toString());
          view.fbSignup();
      }
    },
    function(error) {
      Alert.alert('Login failed with error: ' + error);
    });
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
                <Image style={styles.mark} source={require('../../img/logo2.png')} resizeMode='contain' />
            </View>
            <View style={styles.inputs}>
                <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input, styles.whiteFont]}
                        placeholder="Username"
                        placeholderTextColor="#FFF"
                        onChangeText={(username) => this.setState({username})}
                        value={this.state.username}
                    />
                </View>
                <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input, styles.whiteFont]}
                        placeholder="Email"
                        placeholderTextColor="#FFF"
                        onChangeText={(email) => this.setState({email})}
                        value={this.state.email}
                        keyboardType='email-address'
                    />
                </View>
                <View style={styles.inputContainer}>
                    <TextInput
                        secureTextEntry={true}
                        style={[styles.input, styles.whiteFont]}
                        placeholder="Password"
                        placeholderTextColor="#FFF"
                        onChangeText={(password) => this.setState({password})}
                        value={this.state.password}
                    />
                </View>
                <View style={styles.inputContainer}>
                    <TextInput
                        secureTextEntry={true}
                        style={[styles.input, styles.whiteFont]}
                        placeholder="Verify Password"
                        placeholderTextColor="#FFF"
                        onChangeText={(verifyPassword) => this.setState({verifyPassword})}
                        value={this.state.verifyPassword}
                    />
                </View>
            </View>
              <TouchableHighlight style={styles.signin} underlayColor='#840032' onPress={this.gotoNext.bind(this)}>
                <Text style={styles.whiteFont}>Next</Text>
              </TouchableHighlight>
              <View style={styles.signup}>
              <TouchableOpacity onPress={this.signIn.bind(this)}>
                <Text style={styles.greyFont}>Already have an account?<Text style={styles.whiteFont}>  Sign in</Text></Text>
              </TouchableOpacity>
            </View>
        </View>
    );
  }
  gotoNext() {
    if (!isValidUsername(this.state.username)) {
      Alert.alert("Invalid username: username must be at least 6 alphanumeric characters")
    } 
    else if (!isPasswordMatch(this.state.password, this.state.verifyPassword)) {
      Alert.alert("Passwords do not match.")
    }
    else if (!isValidPassword(this.state.password)) {
      Alert.alert("Invalid password: password must be at least 8 characters, with" +
                  "at least one uppercase letter, at least one number, and at least one" +
                  "of !, @, #, $, or ?")
    } else if (!isValidEmail(this.state.email)) {
      Alert.alert("Invalid email address.")
    }
    else {
      this.props.navigator.setState({
        username: this.state.username,
        password: this.state.password,
        email: this.state.email
      });
      this.props.navigator.push({
        id: 'SignUp2',
        name: 'SignUpSecond',
      });
    }
  }

  fbSignup() {
    this.props.navigator.push({
        id: 'EventFeed',
        name: 'Events',
      });
  }

  signIn() {
    this.props.navigator.popToTop();
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
      //backgroundColor: '#A9A9A9'
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
        alignItems: 'center'
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
    facebook: {
        backgroundColor: '#3b5998',
        padding: 20,
        alignItems: 'center'
    }
});