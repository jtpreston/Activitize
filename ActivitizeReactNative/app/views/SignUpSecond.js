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

var background = require('../../img/login.jpeg');

export class SignUpSecond extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      firstName: '',
      lastName: '',
      dob: '',
      phone: '',
      eamil: ''
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
            <View style={styles.header}>
                <Image style={styles.mark} source={require('../../img/logo2.png')} />
            </View>
            <View style={styles.inputs}>
                <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input, styles.whiteFont]}
                        placeholder="Email Address"
                        placeholderTextColor="#FFF"
                        onChangeText={(email) => this.setState({email})}
                        value={this.state.email}
                    />
                </View>
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
                        placeholder="Last Name"
                        placeholderTextColor="#FFF"
                        onChangeText={(lastName) => this.setState({lastName})}
                        value={this.state.lastName}
                    />
                </View>
                <View style={styles.inputContainer}>
                    <TextInput
                        style={[styles.input, styles.whiteFont]}
                        placeholder="Date of Birth"
                        placeholderTextColor="#FFF"
                        onChangeText={(dob) => this.setState({dob})}
                        value={this.state.dob}
                    />
                </View>
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
              <TouchableHighlight style={styles.signin} underlayColor='#BFE9DB' onPress={this.gotoNext.bind(this)}>
                <Text style={styles.whiteFont}>Sign up</Text>
              </TouchableHighlight>
              <TouchableHighlight style={styles.back} underlayColor='#BFE9DB' onPress={this.back.bind(this)}>
                <Text style={styles.whiteFont}>Go back</Text>
              </TouchableHighlight>
        </View>
    );
  }
  gotoNext() {
      let navigator = this.props.navigator;

      var params;
      if (this.state.phone) {
        params = {
          username: this.props.navigator.state.username,
          password: this.props.navigator.state.password,
          firstName: this.state.firstName,
          lastName: this.state.lastName,
          age: this.state.dob,
          email: this.state.email,
          phoneNumber: this.state.phone,
          numberOfFriends: '0',
          usingFacebook: 'false'
        };
      } else {
        params = {
          username: this.props.navigator.state.username,
          password: this.props.navigator.state.password,
          firstName: this.state.firstName,
          lastName: this.state.lastName,
          age: this.state.dob,
          email: this.state.email,
          numberOfFriends: '0',
          usingFacebook: 'false'
        };
      }
    // fetch('https://activitize.net/activitize/user/createUser', {
    //   method: 'POST',
    //   headers: {
    //     'Accept': 'application/json',
    //     'Content-Type': 'application/json',
    //   },
    //   body: JSON.stringify(params)
    // })
    // .then(function(response) {
    //   var obj = JSON.parse(response.json());
    //   Alert.alert(obj.status.toString());
    //   // return response.json().then(function(json) {
    //   // if (json.status.parse() === "OK") {
    //   //   Alert.alert("Create user success");
    //   //   navigator.push({
    //   //     id: 'EventFeed',
    //   //     name: 'Events',
    //   //   });
    //   // } else {
    //   //   Alert.alert(json.errorMessage.toString());
    //   // }
    //   // });
    // })
    // .catch((error) => {
    //     console.error(error);
    //   });
      this.props.navigator.push({
        id: 'EventFeed',
        name: 'Events',
      });
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
      backgroundColor: '#03414D'
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
        backgroundColor: '#547980',
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
    back: {
        backgroundColor: '#808080',
        padding: 20,
        alignItems: 'center'
    }
});