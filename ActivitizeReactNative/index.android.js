/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

/**
 * Our colors: https://coolors.co/2b4162-840032-e07e06-1a1423-99ddc8
 * 2B4162 -- blue
 * 840032 -- burgundy
 * E07E06 -- orange
 * 1A1423 -- dark blue
 * 99DDC8 -- robin egg blue
 * other:
 * FFF -- white
 * 696969 -- dark grey
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Navigator
} from 'react-native';

'use strict';

import { Login } from './app/views/login'
import { EventFeed } from './app/views/eventfeed'
import { NewEvent } from './app/views/newevent'
import { SettingsPage } from './app/views/settingspage'
import { SignUp } from './app/views/signup'
import { EditProfile } from './app/views/EditProfile'
import { EventView } from './app/views/EventView'
import { EditEventView } from './app/views/EditEventView'
import { SignUpSecond } from './app/views/SignUpSecond'
import { ForgotPassword } from './app/views/ForgotPassword'

class ActivitizeReactNative extends React.Component {
  render() {
    return (
      <Navigator
          initialRoute={{id: 'Login', name: 'Login'}}
          renderScene={this.renderScene.bind(this)}
          configureScene={(route) => {
            if (route.sceneConfig) {
              return route.sceneConfig;
            }
            return Navigator.SceneConfigs.FadeAndroid;
          }} />
    );
  }
  renderScene(route, navigator) {
    var routeId = route.id;

    if (routeId === 'Login') {
      return (
        <Login
          navigator={navigator} />
      );
    }
    if (routeId === 'EventFeed') {
      return (
        <EventFeed
          navigator={navigator} />
      );
    }
    if (routeId === 'NewEvent') {
      return (
        <NewEvent
          navigator={navigator} />
      );
    }
    if (routeId === 'SettingsPage') {
      return (
        <SettingsPage
          navigator={navigator} />
      );
    }
    if (routeId === 'SignUp') {
      return (
        <SignUp
          navigator={navigator} />
      );
    }
    if (routeId === 'EditProfile') {
      return (
        <EditProfile
          navigator={navigator} />
      );
    }
    if (routeId === 'EventView') {
      return (
        <EventView 
          navigator={navigator} />
      );
    }
    if (routeId === 'EditEvent') {
      return (
        <EditEventView 
          navigator={navigator} />
      );
    }
    if (routeId === 'SignUp2') {
      return (
        <SignUpSecond 
          navigator={navigator} />
      );
    }
    if (routeId === 'ForgotPassword') {
      return (
        <ForgotPassword
          navigator={navigator} />
      );    
    }
  }
}

AppRegistry.registerComponent('ActivitizeReactNative', () => ActivitizeReactNative);
