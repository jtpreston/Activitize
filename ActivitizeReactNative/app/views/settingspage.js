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
  AsyncStorage
} from 'react-native';

async function getParams(navigator) {
  var token = await AsyncStorage.getItem('xcsrfToken');
  var cookie = await AsyncStorage.getItem('jsessionid');
  var memory = await AsyncStorage.getItem('remember');
  return {token: token, cookie: cookie, memory: memory};
}

async function clearStorage() {
  await AsyncStorage.removeItem('xcsrfToken');
  await AsyncStorage.removeItem('jsessionid');
  await AsyncStorage.removeItem('remember');
}

export class SettingsPage extends React.Component{
  constructor(props) {
    super(props);
    this.state = {};
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
      <View style={{flex: 1, marginTop: 50}}>
        <View style={styles.profile_info}>
          <View>
          <TouchableHighlight onPress={this.viewProfile.bind(this)} style={styles.button} underlayColor='#BFE9DB'>
            <Text style={styles.display}>My Profile</Text>
          </TouchableHighlight>
          </View>
          <View>
          <TouchableHighlight onPress={this.editProfile.bind(this)} style={styles.button} underlayColor='#BFE9DB'>
            <Text style={styles.display}>Edit Profile</Text>
          </TouchableHighlight>
          </View>
          <View>
          <TouchableHighlight onPress={this.viewEvents.bind(this)} style={styles.button} underlayColor='#BFE9DB'>
            <Text style={styles.display}>My Events</Text>
          </TouchableHighlight>
          </View>
          <View>
          <TouchableHighlight onPress={this.viewFriends.bind(this)} style={styles.button} underlayColor='#BFE9DB'>
            <Text style={styles.display}>My Friends</Text>
          </TouchableHighlight>
          </View>
        </View>
      </View>
    );
  }

  editProfile() {
    this.props.navigator.push({
      id: 'EditProfile',
      name: 'EditProfile',
    });
  }

  viewEvents() {

  }

  viewFriends() {

  }

  viewProfile() {
     this.props.navigator.push({
      id: 'Profile',
      name: 'Profile',
    });
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
          onPress={async () => {
            var params = await getParams(navigator.parentNavigator);
            // console.log("xcsrf parent: "+ params.token)
            // console.log("cookie parent: "+ params.cookie)
            // console.log("remember parent: "+ params.memory)

        var nav = navigator.parentNavigator;

        var headers = {
            'Accept': 'application/json',
            Cookie: params.cookie,
            'X-CSRF-TOKEN': params.token
          }
        // console.log("headers: " + JSON.stringify(headers))

        return fetch('https://activitize.net/activitize/user/logout', {
          method: 'GET',
          headers: headers
        })
        .then(function(response) {
          console.log("status: " + response.status)
          return response.json()
        })
        .then(async function(json) {
          console.log("response: " + json.responseStatus);
      console.log("error: " + json.errorMessage);
          if (json.responseStatus === "OK") {
            await clearStorage();
            nav.popToTop();
          }
        })
        .catch((error) => {
          console.error(error);
        });
      }}>
        <Text style={{color: 'white', margin: 10,}}>
          Log out
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
        alignItems: 'center',
        justifyContent: 'center',
        marginTop: 20,
        flexDirection: 'column'
    },
    name: {
      color: '#1A1423', 
      fontSize: 24
    },
    button: {
        backgroundColor: '#2B4162',
        padding: 10,
        marginLeft: 10,
        marginRight: 10,
        marginTop: 30,
        width: 300
    }
});
