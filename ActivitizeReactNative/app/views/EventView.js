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
  Alert,
  ScrollView
} from 'react-native';

var navProps;

export class EventView extends React.Component{
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
    navProps = this.props.navigator.state;
    return (
      <View style={styles.container}>
      <ScrollView>
        <View style={styles.textContainer}>
        <Text style={styles.title}>{this.props.navigator.state.e_name}</Text>
        <Text style={styles.date}>{this.props.navigator.state.e_date}, {this.props.navigator.state.e_time} to</Text>
        <Text style={styles.date}>{this.props.navigator.state.e_date2}, {this.props.navigator.state.e_time2}</Text>
        <Text style={styles.location}>at {this.props.navigator.state.e_location}</Text>
        <Text style={styles.description}>Description: {this.props.navigator.state.e_description}</Text>
        </View>
      </ScrollView>
        <TouchableHighlight style={styles.mute} underlayColor='#BFE9DB'>
          <Text style={styles.whiteFont}>Mute Notifications</Text>
        </TouchableHighlight>
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
            navigator.setState({
              e_id: navigator.parentNavigator.state.e_id,
              e_name: navigator.parentNavigator.state.e_name,
              e_date: navigator.parentNavigator.state.e_date,
              e_time: navigator.parentNavigator.state.e_time,
              e_date2: navigator.parentNavigator.state.e_date2,
              e_time2: navigator.parentNavigator.state.e_time2,
              e_location: navigator.parentNavigator.state.e_location,
              e_description: navigator.parentNavigator.state.e_location
            });
            navigator.parentNavigator.push({
            id: 'EditEvent',
            name: 'EditEvent',
            });
          }}>
        <Text style={{color: 'white', margin: 10,}}>
          Edit
        </Text>
      </TouchableOpacity>
    );
  },
  /*Button(route, navigator, index, navState) {
    <TouchableOpacity style={{flex: 1, justifyContent: 'center'}}
      onPress={() => {
	navigator.setState({navigator.parentNavigator.push({
	  id: 'favorite',
	  name: 'favorite',
	});
      }}
      <Text style={{color: 'white', margin: 10,}}>
          Favorite
      </Text>
    </TouchableOpacity>
   );
  },*/
  Title(route, navigator, index, navState) {
    return (
      <TouchableOpacity style={{flex: 1, justifyContent: 'center'}}>
      </TouchableOpacity>
    );
  }
};

var styles = StyleSheet.create({
    container: {
      flexDirection: 'row',
      height: windowSize.height, 
      padding: 20, 
      marginTop: 55, 
    },
    textContainer: {
      width: windowSize.width - 40
    },
    title: {
      color: '#1A1423',
      fontSize: 30
    },
    date: {
      color: '#1A1423',
      fontSize: 20,
      marginTop: 10
    },
    location: {
      color: '#1A1423',
      fontSize: 20,
      marginTop: 10 
    },
    description: {
      color: '#1A1423',
      fontSize: 16,
      marginTop: 10 
    },
    mute: {
    	backgroundColor: '#E07E06',
    	padding: 20,
      alignItems: 'center',
      position: 'absolute',
      bottom: 0,
      left: 0,
      right: 0,
    },
    whiteFont: {
      color: '#FFF'
    }
});
