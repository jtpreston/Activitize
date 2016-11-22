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
  Alert,
  TimePickerAndroid
} from 'react-native';

var stateVars;

function getFormattedTime(hour, minute) {
  return (hour > 12 ? (hour - 12) : hour ) + ':' + (minute < 10 ? '0' + minute : minute) + (hour > 12 ? ' p.m.' : ' a.m.');
}

function getDate(dateString) {
  var month = parseInt(dateString.substring(0, dateString.indexOf('/')));
  var day = parseInt(dateString.substring(dateString.indexOf('/') + 1, dateString.lastIndexOf('/')));
  var year = parseInt(dateString.substring(dateString.lastIndexOf('/') + 1, dateString.length));
  var date = new Date();
  date.setMonth(month - 1);
  date.setDate(day);
  date.setYear(year);
  return date;
}

function getHour(timeString) {
  var hour = timeString.substring(0, timeString.indexOf(':'));
  var ampm = timeString.substring(timeString.indexOf(' '), timeString.length);
  return ampm === 'a.m.' ? parseInt(hour) : (parseInt(hour) + 12);
}

function getMinute(timeString) {
  return parseInt(timeString.substring(timeString.indexOf(':') + 1, timeString.indexOf(' ')));
}

export class EditEventView extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      eventName: this.props.navigator.state.e_name,
      holdDate: this.props.navigator.state.e_date,
      dateText: this.props.navigator.state.e_date,
      time: this.props.navigator.state.e_time,
      date: getDate(this.props.navigator.state.e_date),
      hour: getHour(this.props.navigator.state.e_time),
      minute: getMinute(this.props.navigator.state.e_time)
    };
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
    stateVars = this.state;
    return (
      <View style={styles.container}>
            <View style={styles.inputs}>
                <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input]}
                        onChangeText={(e_name) => this.props.navigator.setState({e_name})}
                        value={this.props.navigator.state.e_name}
                    />
                </View>
                <TouchableWithoutFeedback
                    onPress={this.showPicker.bind(this, {date: this.state.date})}>
                    <View style={styles.inputContainer}>
                    <Text style={styles.input}>{this.state.dateText}</Text>
                    </View>
                    </TouchableWithoutFeedback>
          <TouchableWithoutFeedback
            onPress={this.showTimePicker.bind(this, {
              hour: this.state.hour,
              minute: this.state.minute,
            })}>
            <View style={styles.inputContainer}>
                    <Text style={styles.input}>{this.props.navigator.state.e_time}</Text>
                    </View>
          </TouchableWithoutFeedback>
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
        var date = new Date();
        date.setMonth(month);
        date.setDate(day);
        date.setYear(year);
        this.setState({date: date});
        //newState[stateKey + 'Text'] = date.toLocaleDateString();
        var dateString = (month + 1) + "/" + day + "/" + year;
        this.setState({dateText: dateString});
        this.props.navigator.setState({e_date: dateString});
      }
    } catch ({code, message}) {
      console.warn('Error ', message);
    }
  };

  showTimePicker = async (options) => {
    try {
      const {action, minute, hour} = await TimePickerAndroid.open(options);
      if (action === TimePickerAndroid.timeSetAction) {
        this.props.navigator.setState({e_time: getFormattedTime(hour, minute)});
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
          onPress={() => {
            navigator.parentNavigator.setState({
              e_name: stateVars.eventName,
              e_date: stateVars.holdDate,
              e_time: stateVars.time
            });
            navigator.parentNavigator.pop()}}>
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
        fontSize: 20,
        color: '#1A1423'
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
    }
});