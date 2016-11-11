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

import { ScrollableEventView } from './ScrollableEventView'
import { EventView } from './EventView'

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
      <ScrollView style={{marginTop: 55, marginBottom: 15}}>
          <ScrollableEventView eventId='1' name='A really cool event' date='Nov 19, 2016' time='5:00 p.m.' 
              onPress={() => this.viewEvent('1', 'A really cool event', 'Nov 19, 2016', '5:00 p.m.')}/>   
          <ScrollableEventView eventId='2' name='Another cool event' date='Dec 5, 2016' time='7:32 p.m.' 
              onPress={() => this.viewEvent('2', 'Another cool event', 'Dec 5, 2016', '7:32 p.m.')}/>   
          <ScrollableEventView eventId='3' name='A not so cool event' date='Jan 20, 2017' time='9:00 a.m.' 
              onPress={() => this.viewEvent('3', 'A not so cool event', 'Jan 20, 2017', '9:00 a.m.')}/>   
      
      </ScrollView>
    );
  }

  viewEvent(eventId, eventName, date, time) {
    this.props.navigator.setState({
      e_id: eventId,
      e_name: eventName,
      e_date: date,
      e_time: time
    });
    this.props.navigator.push({
      id: 'EventView',
      name: 'EventView'
    });
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