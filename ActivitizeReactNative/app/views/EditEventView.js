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
  TimePickerAndroid,
  ScrollView
} from 'react-native';

var dismissKeyboard = require('dismissKeyboard');

var stateVars;

function getFormattedTime(hour, minute) {
  return (hour > 12 ? (hour - 12) : hour ) + ':' + (minute < 10 ? '0' + minute : minute) + (hour >= 12 ? ' p.m.' : ' a.m.');
}

function getDate(dateString) {
  var month = parseInt(dateString.substring(0, dateString.indexOf('/')));
  var day = parseInt(dateString.substring(dateString.indexOf('/') + 1, dateString.lastIndexOf('/')));
  var year = parseInt(dateString.substring(dateString.lastIndexOf('/') + 1, dateString.length));
  var date = new Date();
  date.setMonth(month - 1);
  date.setDate(day);
  date.setYear(year);
  console.log("date: " + date.toString())
  return date;
}

function getHour(timeString) {
  var hour = timeString.substring(0, timeString.indexOf(':'));
  var ampm = timeString.substring(timeString.indexOf(' ') + 1, timeString.length);
  hour = ampm === 'a.m.' ? parseInt(hour) : (parseInt(hour) + 12);
  console.log("hour: " + hour)
  return hour;
}

function getMinute(timeString) {
  var minute = parseInt(timeString.substring(timeString.indexOf(':') + 1, timeString.indexOf(' ')));
  console.log("minute: " + minute)
  return minute;
}

export class EditEventView extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      eventName: this.props.navigator.state.e_name,
      holdDate: this.props.navigator.state.e_date,
      holdDate2: this.props.navigator.state.e_date2,
      dateText: this.props.navigator.state.e_date,
      dateText2: this.props.navigator.state.e_date2,
      time: this.props.navigator.state.e_time,
      time2: this.props.navigator.state.e_time2,
      date: getDate(this.props.navigator.state.e_date),
      date2: getDate(this.props.navigator.state.e_date2),
      hour: getHour(this.props.navigator.state.e_time),
      minute: getMinute(this.props.navigator.state.e_time),
      hour2: getHour(this.props.navigator.state.e_time2),
      minute2: getMinute(this.props.navigator.state.e_time2),
      location: this.props.navigator.state.e_location,
      description: this.props.navigator.state.e_description
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
      <ScrollView style={styles.container}>
      <TouchableWithoutFeedback onPress={()=> dismissKeyboard()}>
            <View style={styles.inputs}>
            <View>
            <Text style={styles.label}>Name:</Text>
            </View>
                <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input]}
                        onChangeText={(e_name) => this.props.navigator.setState({e_name})}
                        value={this.props.navigator.state.e_name}
                    />
                </View>
                <View>
                <Text style={styles.label}>Location:</Text>
                </View>
                <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input]}
                        placeholder="Location"
                        onChangeText={(e_location) => this.props.navigator.setState({e_location})}
                        value={this.props.navigator.state.e_location}
                    />
                    </View>
                    <View>
                    <Text style={styles.label}>Description:</Text>
                    </View>
                    <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input]}
                        multiline={true}
                        maxLength={50}
                        numberOfLines={4}
                        placeholder="Description"
                        onChangeText={(e_description) => this.props.navigator.setState({e_description})}
                        value={this.props.navigator.state.e_description}
                    />
                </View>
                <View>
                <Text style={styles.label}>Start Date:</Text>
                </View>
                <TouchableWithoutFeedback
                    onPress={this.showPicker.bind(this, {date: this.state.date, minDate: new Date()})}>
                    <View style={styles.inputContainer}>
                    <Text style={styles.input}>{this.state.dateText}</Text>
                    </View>
                    </TouchableWithoutFeedback>
                    <View>
                    <Text style={styles.label}>Start Time:</Text>
                    </View>
                    <TouchableWithoutFeedback
                      onPress={this.showTimePicker.bind(this, {
                        hour: this.state.hour,
                        minute: this.state.minute,
                      })}>
                      <View style={styles.inputContainer}>
                        <Text style={styles.input}>{this.props.navigator.state.e_time}</Text>
                      </View>
                    </TouchableWithoutFeedback>
                    <View>
                    <Text style={styles.label}>End Date:</Text>
                    </View>
                    <TouchableWithoutFeedback
                    onPress={this.showPicker2.bind(this, {date: this.state.date2, minDate: this.state.date})}>
                    <View style={styles.inputContainer}>
                    <Text style={styles.input}>{this.state.dateText2}</Text>
                    </View>
                    </TouchableWithoutFeedback>
                    <View>
                    <Text style={styles.label}>End Time:</Text>
                    </View>
                    <TouchableWithoutFeedback
                      onPress={this.showTimePicker2.bind(this, {
                        hour: this.state.hour2,
                        minute: this.state.minute2,
                      })}>
                      <View style={styles.inputContainer}>
                              <Text style={styles.input}>{this.props.navigator.state.e_time2}</Text>
                              </View>
                    </TouchableWithoutFeedback>
            </View>
            </TouchableWithoutFeedback>
        </ScrollView>
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
        console.log("date: " + date)
        //newState[stateKey + 'Text'] = date.toLocaleDateString();
        var dateString = (month + 1) + "/" + day + "/" + year;
        this.setState({dateText: dateString});
        this.props.navigator.setState({e_date: dateString});
      }
    } catch ({code, message}) {
      console.warn('Error ', message);
    }
  };

  showPicker2 = async (options) => {
    try {
      const {action, year, month, day} = await DatePickerAndroid.open(options);
      if (action === DatePickerAndroid.dismissedAction) {
        //newState[stateKey + 'Text'] = 'dismissed';
      } else {
        var date = new Date();
        date.setMonth(month);
        date.setDate(day);
        date.setYear(year);
        this.setState({date2: date});
        //newState[stateKey + 'Text'] = date.toLocaleDateString();
        var dateString = (month + 1) + "/" + day + "/" + year;
        this.setState({dateText2: dateString});
        this.props.navigator.setState({e_date2: dateString});
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
        this.setState({hour: hour});
        this.setState({minute: minute});
      } else if (action === TimePickerAndroid.dismissedAction) {
        //newState[stateKey + 'Text'] = 'dismissed';
      }
    } catch ({code, message}) {
      console.warn(`Error in example: `, message);
    }
  };

  showTimePicker2 = async (options) => {
    try {
      const {action, minute, hour} = await TimePickerAndroid.open(options);
      if (action === TimePickerAndroid.timeSetAction) {
        this.props.navigator.setState({e_time2: getFormattedTime(hour, minute)});
        this.setState({hour2: hour});
        this.setState({minute2: minute});
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
              e_time: stateVars.time,
              e_date2: stateVars.holdDate2,
              e_time2: stateVars.time2,
              e_location: stateVars.location,
              e_description: stateVars.description
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
    },
    label: {
        position: 'absolute',
        left: 61,
        top: 12,
        right: 0,
        height: 40,
        fontSize: 10,
        color: '#1A1423'
    }
});