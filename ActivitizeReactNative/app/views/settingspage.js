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
      <View style={{flex: 1, alignItems: 'center', justifyContent:'center'}}>
        <View style={styles.header}>
                <Image style={styles.mark} source={require('../../img/profile_blank.png')} />
        </View>
        <View style={styles.profile_info}>
          <TouchableOpacity onPress={this.editProfile.bind(this)}>
            <Text style={styles.name}>FirstName LastName</Text>
          </TouchableOpacity>
        </View>
        <View style={styles.profile_info}>
          <View>
          <TouchableHighlight style={styles.button} underlayColor='#BFE9DB'>
            <Text style={styles.display}>My Events</Text>
          </TouchableHighlight>
          </View>
          <View>
          <TouchableHighlight style={styles.button} underlayColor='#BFE9DB'>
            <Text style={styles.display}>Friends</Text>
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
          onPress={() => navigator.parentNavigator.popToTop()}>
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
        marginTop: 20,
        alignItems: 'center',
        justifyContent: 'center',
        flexDirection: 'row'
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
        width: 100
    }
});