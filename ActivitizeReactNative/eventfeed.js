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
  ScrollView
} from 'react-native';

export class EventFeed extends React.Component{
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
            <Navigator.NavigationBar style={{backgroundColor: '#547980'}}
                routeMapper={NavigationBarRouteMapper} />
          } />
    );
  }
  renderScene(route, navigator) {
    return (
      <ScrollView>
          <Text style={styles.display}>Sorry! You have no events to show.
          You should start receiving invitations soon, but if you just can't
          wait to begin, try creating a new event.
          </Text>
      </ScrollView>
    );
  }
}

var NavigationBarRouteMapper = {
  LeftButton(route, navigator, index, navState) {
    return (
      <TouchableOpacity style={{flex: 1, justifyContent: 'center'}}
          onPress={() => {
            navigator.parentNavigator.push({
            id: 'SettingsPage',
            name: 'Settings',
            });
          }}>
        <Text style={{color: 'white', margin: 10,}}>
          Profile
        </Text>
      </TouchableOpacity>
    );
  },
  RightButton(route, navigator, index, navState) {
    return (
      <TouchableOpacity style={{flex: 1, justifyContent: 'center'}}
          onPress={() => {
            navigator.parentNavigator.push({
            id: 'NewEvent',
            name: 'NewEvent',
            });
          }}>
        <Text style={{color: 'white', margin: 10,}}>
          New Event
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
    display: {
      marginTop: 100,
      marginLeft: 20,
      marginRight: 20,
      color: '#547980', 
      fontSize: 16
    },
    whiteFont: {
      color: '#FFF'
    },
    noevents: {
        padding: 20,
        alignItems: 'center',
        justifyContent: 'center',
        flex: 1,
    }
});