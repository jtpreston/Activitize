/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
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

import { Login } from './login'
import { EventFeed } from './eventfeed'
import { NewEvent } from './newevent'
import { SettingsPage } from './settingspage'
import { SignUp } from './signup'

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

  }
}

AppRegistry.registerComponent('ActivitizeReactNative', () => ActivitizeReactNative);
