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
  Alert
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
            <Navigator.NavigationBar style={{backgroundColor: '#547980'}}
                routeMapper={NavigationBarRouteMapper} />
          } />
    );
  }
  renderScene(route, navigator) {
    navProps = this.props.navigator.state;
    return (
      <View style={styles.container}>
      <View style={styles.textContainer}>
        <Text style={styles.title}>{this.props.navigator.state.e_name}</Text>
        <Text style={styles.date}>{this.props.navigator.state.e_date}</Text>
        <Text style={styles.time}>{this.props.navigator.state.e_time}</Text>
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
            navigator.setState({
              e_id: navProps.e_id,
              e_name: navProps.e_name,
              e_date: navProps.e_date,
              e_time: navProps.e_time
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
      width: windowSize.width
    },
    title: {
      color: '#808080',
      fontSize: 30
    },
    date: {
      color: '#808080',
      fontSize: 20,
      marginTop: 10
    },
    time: {
      color: '#808080',
      fontSize: 16,
      marginTop: 10 
    }
});
