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
  TimePickerAndroid
} from 'react-native';

function getFormattedTime(hour, minute) {
  return (hour > 12 ? (hour - 12) : hour ) + ':' + (minute < 10 ? '0' + minute : minute) + (hour > 12 ? ' p.m.' : ' a.m.');
}

export class NewEvent extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      eventName: '',
      date: '',
      time: 'Time',
      invite: '',
      dateText: 'Date',
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
                        placeholder="Event Name"
                        onChangeText={(eventName) => this.setState({eventName})}
                        value={this.state.eventName}
                    />
                </View>
                  <TouchableWithoutFeedback
                    onPress={this.showPicker.bind(this, {date: new Date()})}>
                    <View style={styles.inputContainer}>
                    <Text style={styles.date}>{this.state.dateText}</Text>
                    </View>
                    </TouchableWithoutFeedback>
          <TouchableWithoutFeedback
            onPress={this.showTimePicker.bind(this, {
              hour: 12,
              minute: 0,
            })}>
            <View style={styles.inputContainer}>
                    <Text style={styles.date}>{this.state.time}</Text>
                    </View>
          </TouchableWithoutFeedback>
                <View style={styles.inputContainer}>
                    <TextInput
                        style={[styles.input]}
                        placeholder="Invite Friends"
                        onChangeText={(invite) => this.setState({invite})}
                        value={this.state.invite}
                    />
                </View>
            </View>
        </View>
    );
  }
  showPicker = async (options) => {
    try {
      const {action, year, month, day} = await DatePickerAndroid.open(options);
      if (action === DatePickerAndroid.dismissedAction) {
        //newState[stateKey + 'Text'] = 'dismissed';
      } else {
        this.setState({date: new Date(year, month, day)});
        //newState[stateKey + 'Text'] = date.toLocaleDateString();
        var dateString = (month + 1) + "/" + day + "/" + year;
        this.setState({dateText: dateString});
      }
    } catch ({code, message}) {
      console.warn('Error ', message);
    }
  };

  showTimePicker = async (options) => {
    try {
      const {action, minute, hour} = await TimePickerAndroid.open(options);
      if (action === TimePickerAndroid.timeSetAction) {
        this.setState({time: getFormattedTime(hour, minute)});
      } else if (action === TimePickerAndroid.dismissedAction) {
        //newState[stateKey + 'Text'] = 'dismissed';
      }
    } catch ({code, message}) {
      console.warn(`Error in example: `, message);
    }
  };
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
            navigator.parentNavigator.pop();
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
        padding: 20,
        borderWidth: 1,
        borderBottomColor: '#D8D8D8',
        borderColor: 'transparent'
    },
    input: {
        position: 'absolute',
        left: 61,
        top: 12,
        right: 0,
        height: 40,
        fontSize: 12
    },
    date: {
        position: 'absolute',
        left: 61,
        top: 12,
        right: 0,
        height: 40,
        fontSize: 12,
        marginRight: 50,
        color: '#808080'
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