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
  ScrollView,
  AsyncStorage
} from 'react-native';

import { ScrollableEventView } from './ScrollableEventView'
import { EventView } from './EventView'

function convertTime(milliseconds) {
  var date = new Date(milliseconds);
  //console.log("date: " + date.toString())
  return date;
}

function getFormattedDate(date) {
  var month = date.getMonth() + 1;
  var day = date.getDate() + 1;
  var year = date.getFullYear() + 1;
  return month + "/" + day + "/" + year;
} 

function getFormattedTime(date) {
  var hours = date.getHours();
  var minutes = date.getMinutes();

  var time = "a.m.";
  if (hours >= 11) {
    time = "p.m."
  }

  if (hours < 12) {
    hours = hours + 1;
  } else {
    hours = hours - 12;
  }

  return hours + ":" + (minutes < 10 ? "0" + minutes : minutes) + " " + time;
}

export class EventFeed extends React.Component{
  constructor(props) {
    super(props);
    this.state = {events: []};
  }

  getScrollableEventView(event) {
    return (
      <ScrollableEventView key = {event.eventId}
      eventId={event.eventId} 
      name={event.eventName}
      date={event.startDate} time={event.startTime}
      date2={event.endDate} time2={event.endTime}
      location={event.location} 
      description={event.description}
      onPress={() => event.self.viewEvent(event.eventId, event.eventName, event.startDate, event.startTime, event.endDate, event.endTime, event.location, event.description)}/>)
  }

  async componentWillMount() {
    await this.loadEvents();
  }

  async componentWillReceiveProps() {
    await this.loadEvents();
  }

  async loadEvents() {
    var self = this;
    var url = 'https://activitize.net/activitize/events/getAllEventsForUser';
    var cookie = await AsyncStorage.getItem('jsessionid');
    var token = await AsyncStorage.getItem('xcsrfToken');
    // console.log("token: " + token)
    var headers = {
      'Accept': 'application/json',
      Cookie: cookie,
      'X-CSRF-TOKEN': token
    }

    console.log("headers: " + JSON.stringify(headers))

    return fetch(url, {
      method: 'GET',
      headers: headers
    })
    .then(async function(response) {
      console.log("status: " + response.status)
          console.log("xcsrftoken: " + response.headers.get('X-CSRF-TOKEN'));
          await AsyncStorage.setItem('xcsrfToken', response.headers.get('X-CSRF-TOKEN'));
          return response.json();
        })
    .then(function(json) {
      var i, j;
      var events = [];
      for (i = 0; i < json.length; i++) {
        var start = convertTime(json[i][2]);
        var end = convertTime(json[i][3]);
        var startDate = getFormattedDate(start);
        var startTime = getFormattedTime(start);
        var endDate = getFormattedDate(end);
        var endTime = getFormattedTime(end);
        var fields = {
          eventId: json[i][0],
          eventName: json[i][1],
          eventStart: start,
          eventEnd: end,
          startDate: startDate,
          startTime: startTime,
          endDate: endDate,
          endTime: endTime,
          description: json[i][4],
          location: json[i][5],
          priv: json[i][6],
          creator: json[i][10],
          self: self
        }

        events[i] = fields;
      }
      self.setState({events: events});
      self.forceUpdate();
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
      <ScrollView style={{marginTop: 55, marginBottom: 15}}>{this.state.events.map(this.getScrollableEventView)}</ScrollView>
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
