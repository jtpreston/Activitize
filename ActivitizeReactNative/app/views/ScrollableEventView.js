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

export class ScrollableEventView extends React.Component {
	//pass other things we want it to display
	constructor(props, eventId, name, date, time) {
    super(props);
    this.props = props;
    this.state = {
    };
  }
  render() {
    return (
    <TouchableOpacity onPress={this.props.onPress}>
      <View style={styles.container}>
      <View style={styles.textContainer}>
        <Text style={styles.title}>{this.props.name}</Text>
        <Text style={styles.date}>{this.props.date}</Text>
        <Text style={styles.time}>{this.props.time}</Text>
       </View>
       </View>
      </TouchableOpacity>
    );
  }
}

var styles = StyleSheet.create({
    container: {
    	backgroundColor: '#2B4162',
      flexDirection: 'row',
      height: 250, 
      padding: 20, 
      marginTop: 15, 
      marginLeft: 15, 
      marginRight: 15
    },
    textContainer: {
    	width: windowSize.width
    },
    title: {
    	color: '#FFF',
    	fontSize: 30
    },
    date: {
    	color: '#FFF',
    	fontSize: 20,
    	marginTop: 10
    },
    time: {
    	color: '#FFF',
    	fontSize: 16,
    	marginTop: 10	
    }
});