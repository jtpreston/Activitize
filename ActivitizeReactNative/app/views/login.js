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
  NativeModules,
  Alert,
  AsyncStorage
} from 'react-native';

var CookieManager = require('react-native-cookies');

const FBSDK = require('react-native-fbsdk');
const {
  LoginManager,
} = FBSDK;

var background = require('../../img/login5-1.jpg');

export class Login extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      result: '...',
      xcsrf: ''  
    };
  }

  async loginGet() {
    var currentState = this.state;
    var navigator = this.props.navigator;
    var view = this;
    fetch('https://activitize.net/activitize/login', {
      method: 'GET',
    })
    .then(async function(response) {
      console.log("response.status: " + response.status)
      // console.log("X-CSRF-TOKEN: " + response.headers.get('X-CSRF-TOKEN'))

      var xcsrfToken = response.headers.get('X-CSRF-TOKEN');
      await AsyncStorage.setItem('xcsrfToken', xcsrfToken);

      view.getCookie('https://activitize.net/activitize/login', async function(cookie) {
        // console.log("resolved cookie: " + cookie)
        var token = await AsyncStorage.getItem('xcsrfToken');
        // console.log("token: " + token)

        var params = {
          username: currentState.username,
          password: currentState.password,
          'remember-me': 'on'
        }

        var headers = {
          'Content-Type': 'application/x-www-form-urlencoded',
          'X-CSRF-TOKEN': token,
          'Cookie': cookie
        }

        let url = 'https://activitize.net/activitize/login?username=' + params.username + '&password=' + params.password + '&remember-me=on';
        //console.log("url: " + url)

        fetch(url, {
          method: 'POST',
          headers: headers,
        })
        .then(async function(response) {
          console.log("status: " + response.status)
          if (response.ok) {
            xcsrfToken = response.headers.get('X-CSRF-TOKEN');
            await AsyncStorage.setItem('xcsrfToken', xcsrfToken);
            var token = await AsyncStorage.getItem('xcsrfToken');
            console.log("xcsrfToken: " + token)
            view.getCookie('https://activitize.net/activitize/login', async function(cookie) { 
              await AsyncStorage.setItem('jsessionid', cookie);
              var cookie2 = await AsyncStorage.getItem('jsessionid');
              console.log("jsessionid: " + cookie2);
              view.getRemeberMe('https://activitize.net/activitize/login', async function(remember) {
                await AsyncStorage.setItem('remember', remember)
                var memory = await AsyncStorage.getItem('remember');
                console.log("remember-me: " + memory)
                  navigator.push({
                  id: 'EventFeed',
                  name: 'Events'
                });
              })
            })
          }
        })
        .catch((error) => {
          Alert.alert("There was an error logging in.")
          console.log(error);
        });
      })
    })
    .catch((error) => {
      Alert.alert("There was an error logging in.")
      console.log(error);
    });
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
          view.gotoNext();
      }
    },
    function(error) {
      Alert.alert('Login failed with error: ' + error);
    });
  }
  
  getCookie(url, callback) {
    CookieManager.get(url, (err, res) => {
      //console.log('Got cookies for url ', res);
      var cookie = 'JSESSIONID=' + res.JSESSIONID;
      //console.log("cookie: " + cookie)
      callback(cookie);
    });
  }

  getRemeberMe(url, callback) {
    CookieManager.get(url, (err, res) => {
      //console.log('Got cookies for url ', res);
      var remember = 'remember-me=' + res['remember-me'];
      //console.log("remember-me: " + remember)
      callback(remember);
    });
  }

  async hasRememberMe() {
    var remember = await AsyncStorage.getItem('remember');
    console.log("remember: " + remember);
    if (remember !== null) {
      return true;
    } else {
      return false;
    }
  }

  async componentWillMount() {
    var val = await this.hasRememberMe();
    if (val) {
      this.gotoNext();
    }  
  }

  componentWillReceiveProps(props) {
    this.setState({username: ''});
    this.setState({password: ''});
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
                        placeholder="Username"
                        placeholderTextColor="#FFF"
                        onChangeText={(username) => this.setState({username})}
                        value={this.state.username}
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
              </View>
 	      <TouchableOpacity style={styles.signup} onPress={this.forgotPassword.bind(this)}>
                <Text style={styles.whiteFont}>Forgot Password ?</Text>
              </TouchableOpacity>
              <TouchableHighlight style={styles.signin} underlayColor='#840032' onPress={this.loginGet.bind(this)}>
                <Text style={styles.whiteFont}>Sign in</Text>
              </TouchableHighlight>
            <View style={styles.signup}>
              <TouchableOpacity onPress={this.signUp.bind(this)}>
                <Text style={styles.greyFont}>Don't have an account?<Text style={styles.whiteFont}>  Sign up</Text></Text>
              </TouchableOpacity>
            </View>
        </View>
    );
  }

  gotoNext() {

    // let navigator = this.props.navigator;
    // var tokenResult = this.loginGet(); 
    // // tokenResult.then(function(val) {
    // //   navigator.setState({xcsrf: val});
    // // });
    // // console.log("xcsrf: " + this.props.navigator.state.xcsrf)
    // // var params = {
    // //   username: this.state.username,
    // //   password: this.state.password,
    // //   'remember-me': 'on'
    // // };
    // //var loginResult = this.login(params, xcsrf);
    this.props.navigator.push({
      id: 'EventFeed',
      name: 'Events'
    });
  }

  signUp() {
    this.props.navigator.push({
      id: 'SignUp',
      name: 'SignUp',
    });
  }
  forgotPassword() {
    this.props.navigator.push({
      id: 'ForgotPassword',
      name: 'ForgotPassword',
    });
  }
  /*feedback() {
     this.props.navigator.push({
       id: 'feedback',
       name: 'feedback',
     });	
  }*/
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
        alignItems: 'center'
    },
    signup: {
      justifyContent: 'center',
      alignItems: 'center',
      flex: .15
    },
    inputs: {
        marginTop: 10,
        marginBottom: 10,
        flex: .25
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

// hold facebook button code here. just in case.
// <TouchableHighlight style={styles.facebook} underlayColor='#840032' onPress={this.login.bind(this)}>
//                 <Text style={styles.whiteFont}>Sign in with Facebook</Text>
//               </TouchableHighlight>
