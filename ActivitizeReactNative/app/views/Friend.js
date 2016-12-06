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

export class Friend extends React.Component{
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
        <View style={styles.textContainer}>
        
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
          onPress={() => navigator.parentNavigator.pop()}>
        <Text style={{color: 'white', margin: 10,}}>
          Done
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
      color: '#1A1423',
      fontSize: 30
    },
    date: {
      color: '#1A1423',
      fontSize: 20,
      marginTop: 10
    },
    time: {
      color: '#1A1423',
      fontSize: 16,
      marginTop: 10 
    },
    signin: {
    	backgroundColor: '#E07E06',
	padding: 20,
	alignItems: 'center',
    }
});
