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
            <Navigator.NavigationBar style={{backgroundColor: '#E07E06'}}
                routeMapper={NavigationBarRouteMapper} />
          } />
    );
  }
  renderScene(route, navigator) {
    return (
      <ScrollView style={{marginTop: 55, marginBottom: 15}}>
          <ScrollableEventView eventId='1' name='Everyone hate on Eagleton' date='12/8/2016' time='9:00 a.m.' date2='12/8/2016' time2='10:00 a.m.' location='Pawnee, IN' description='Ann, you beautiful tropical fish'
              onPress={() => this.viewEvent('1', 'Everyone hate on Eagleton', '12/8/2016', '9:00 a.m.', '12/8/2016', '10:00 a.m.', 'Pawnee, IN', 'Ann, you beautiful tropical fish')}/>   
          <ScrollableEventView eventId='2' name='Legendary Event' date='12/19/2016' time='10:00 a.m.' date2='12/22/2016' time2='10:00 a.m.' location='MacLarens' description='Featuring a reading of the Bro Code'
              onPress={() => this.viewEvent('2', 'Legendary Event', '12/19/2016', '10:00 a.m.', '12/22/2016', '10:00 a.m.', 'MacLarens', 'Featuring a reading of the Bro Code')}/>   
          <ScrollableEventView eventId='3' name='Make Friends' date='1/20/2017' time='9:00 a.m.' date2='1/21/2017' time2='5:00 p.m.' location='Central Perk' description='so no one told you life was gonna be this way *clap clap clap clap*'
              onPress={() => this.viewEvent('3', 'Make Friends', '1/20/2017', '9:00 a.m.', '1/21/2016', '5:00 p.m.', 'Central Perk', 'so no one told you life was gonna be this way *clap clap clap clap*')}/>   
      
      </ScrollView>
    );
  }

  viewEvent(eventId, eventName, date, time, date2, time2, location, description) {
    this.props.navigator.setState({
      e_id: eventId,
      e_name: eventName,
      e_date: date,
      e_time: time,
      e_date2: date2,
      e_time2: time2,
      e_location: location,
      e_description: description
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
          Settings
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
