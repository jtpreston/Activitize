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
  TouchableHighlight
} from 'react-native';

var background = require('../../img/login5-1.jpg');

export class ForgotPassword extends React.Component{
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
       	                secureTextEntry={true}
       	                style={[styles.input, styles.whiteFont]}
       	                placeholder="New Password"
       	                placeholderTextColor="#FFF"
                        onChangeText={(password) => this.setState({password})}
                        value={this.state.password}
       	            />
       	        </View>
       	        <View style={styles.inputContainer}>
       	            <TextInput
       	                secureTextEntry={true}
       	                style={[styles.input, styles.whiteFont]}
       	                placeholder="Verify New Password"
       	                placeholderTextColor="#FFF"
        	        onChangeText={(verifyPassword) => this.setState({verifyPassword})}
        	        value={this.state.verifyPassword}
                    />
       	        </View>
      	</View>
	</View>
    );
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
          onPress={() => {
            navigator.parentNavigator.pop();
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
