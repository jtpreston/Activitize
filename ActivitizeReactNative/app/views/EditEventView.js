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
  DatePickerAndroid,
  TouchableWithoutFeedback,
  Alert
} from 'react-native';

export class EditEventView extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      eventName: this.props.navigator.state.e_name,
      date: this.props.navigator.state.e_date,
      time: this.props.navigator.state.e_time
    };
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
      <View style={styles.container}>
            <View style={styles.inputs}>
                <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input]}
                        onChangeText={(eventName) => this.setState({eventName})}
                        value={this.state.eventName}
                    />
                </View>
                <View style={styles.inputContainer}>
                  <TextInput
                        style={[styles.input]}
                        onChangeText={(date) => this.setState({date})}
                        value={this.state.date}
                    />
                </View>
                <View style={styles.inputContainer}>
                    <TextInput
                        style={[styles.input]}
                        onChangeText={(time) => this.setState({time})}
                        value={this.state.time}
                    />
                </View>
            </View>
        </View>
    );
  }
  showPicker = async () => {
    try {
      const {action, year, month, day} = await DatePickerAndroid.open(options);
      if (action === DatePickerAndroid.dismissedAction) {
        //newState[stateKey + 'Text'] = 'dismissed';
      } else {
        this.setState({date: new Date(year, month, day)});
        //newState[stateKey + 'Text'] = date.toLocaleDateString();
      }
    } catch ({code, message}) {
      console.warn('Error ', message);
    }
  };
  submit() {
    this.props.navigator.push({
      id: 'EventFeed',
      name: 'Events',
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
          onPress={() => {
            navigator.parentNavigator.push({
            id: 'EventView',
            name: 'EventView',
            });
          }}>
        <Text style={{color: 'white', margin: 10,}}>
          Done
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
    bg: {
        position: 'absolute',
        left: 0,
        top: 0,
        width: windowSize.width,
        height: windowSize.height
    },
    header: {
        justifyContent: 'center',
        alignItems: 'center',
        flex: .5,
        backgroundColor: 'transparent'
    },
    mark: {
        width: 150,
        height: 150
    },
    signin: {
        backgroundColor: '#547980',
        padding: 20,
        alignItems: 'center'
    },
    signup: {
      justifyContent: 'center',
      alignItems: 'center',
      flex: .15
    },
    inputs: {
        marginTop: 100,
        marginBottom: 10,
        flex: .25
    },
    inputPassword: {
        marginLeft: 15,
        width: 20,
        height: 21
    },
    inputUsername: {
      marginLeft: 15,
      width: 20,
      height: 20
    },
    inputContainer: {
        padding: 30,
        borderWidth: 1,
        borderColor: 'transparent',
        marginTop: 10
    },
    input: {
        position: 'absolute',
        left: 61,
        top: 12,
        right: 0,
        height: 40,
        fontSize: 20
    },
    forgotContainer: {
      alignItems: 'flex-end',
      padding: 15,
    },
    greyFont: {
      color: '#D8D8D8'
    },
    whiteFont: {
      color: '#FFF'
    }
});