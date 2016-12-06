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
  ScrollView,
  AsyncStorage
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

function utcDate(date, time) {
  var tokens = time.split(" ");
  var clock = tokens[0];
  var suffix = tokens[1];
  var hour = parseInt(clock.substring(0, clock.indexOf(':')));
  var minutes = clock.substring(clock.indexOf(':') + 1, clock.length);
  var day = date.getDate();
  var month = date.getMonth() + 1;
  var year = date.getFullYear();

  if (suffix === "p.m." && hour !== 12) {
    console.log("hour: " + hour)
    hour = hour + 12;
    console.log("hour: " + hour)
  }

  var hourStr;
  if (hour < 10) {
    hourStr = "0" + hour;
  } else {
    hourStr = hour;
  }

  var dayStr = day;
  if (day < 10) {
    dayStr = "0" + day;
  }

  var monthStr = month;
  if (month < 10) {
    monthStr = "0" + month;
  }

  var offset = date.getTimezoneOffset() / 60;

  var str = year + "-" + monthStr + "-" + dayStr + "T" + hourStr + ":" + minutes + ":00";
  if (offset < 0) {
    offset = offset * -1;
    if (offset < 10) {
      str = str + "+0" + offset + ":00";
    } else {
      str = str + "+" + offset + ":00";
    }
  } else if (offset < 10) {
    str = str + "-0" + offset + ":00";
  } else {
    str = str + "-" + offset + ":00";
  }

  return str;
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
            <View>
            <TouchableHighlight style={styles.signin} underlayColor='#840032' onPress={this.deleteEvent.bind(this)}>
                <Text style={styles.whiteFont}>Delete Event</Text>
              </TouchableHighlight>
              </View>
        </ScrollView>
    );
  }

  deleteEvent() {
    Alert.alert(
      'Delete event?',
      'Are you sure you want to delete this event?',
      [
      {text: 'No', onPress: () => this.cancelDeleteEvent(), style: 'cancel'},
      {text: 'Yes', onPress: () => this.proceedDeleteEvent()}
      ]
      )
  }

  cancelDeleteEvent() {
    console.log("no pressed")
  }

  async proceedDeleteEvent() {
    console.log("yes pressed")
    var navigator = this.props.navigator;
    var self = this;
    var url = 'https://activitize.net/activitize/events/deleteEvent';
    var cookie = await AsyncStorage.getItem('jsessionid');
    var token = await AsyncStorage.getItem('xcsrfToken');
    var headers = {
      Cookie: cookie,
      'X-CSRF-TOKEN': token
    }

    console.log("headers: " + headers)

    fetch (url, {
      method: 'GET',
      headers: headers,
    })
    .then(async function(response) {
      console.log("status: " + response.status)
      console.log("xcsrftoken: " + response.headers.get('X-CSRF-TOKEN'));
      console.log("setting xcsrfToken")
      await AsyncStorage.setItem('xcsrfToken', response.headers.get('X-CSRF-TOKEN'));

      var token = await AsyncStorage.getItem('xcsrfToken');

      var headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        Cookie: cookie,
        'X-CSRF-TOKEN': token
      }

      var params = {
        eventId: navigator.state.e_id
      }

      console.log("headers: " + JSON.stringify(headers))
      console.log("params: " + JSON.stringify(params))

      fetch(url, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(params)
      })
      .then(async function(response) {
        console.log("status: " + response.status)
        console.log("xcsrfToken: " + response.headers.get("X-CSRF-TOKEN"))
        if (response.ok) {
          console.log("setting token")
          await AsyncStorage.setItem("xcsrfToken", response.headers.get("X-CSRF-TOKEN"));
        }
        return response.json()
      })
      .then(async function(json) {
        console.log("response status: " + json.responseStatus)
        if (json.responseStatus === "OK") {
          navigator.popN(2);
        } else {
          Alert.alert(json.errorMessage);
        }
      })
      .catch((error) => {
        Alert.alert("There was an error deleting the event")
        console.log(error);
      });
    })
    .catch((error) => {
      Alert.alert("There was an error deleting the event")
      console.log(error);
    });
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
      onPress={async () => {
        var startDate = utcDate(stateVars.date, navigator.parentNavigator.state.e_time);
        var endDate = utcDate(stateVars.date2, navigator.parentNavigator.state.e_time2);
        console.log("date: " + startDate)
        console.log("date2: " + endDate)

        var nav = navigator.parentNavigator;
        var url = 'https://activitize.net/activitize/events/editEvent';
        var cookie = await AsyncStorage.getItem('jsessionid');
        var token = await AsyncStorage.getItem('xcsrfToken');

        var headers = {
          Cookie: cookie,
          'X-CSRF-TOKEN': token
        }

        console.log("headers: " + headers)

        fetch (url, {
          method: 'GET',
          headers: headers,
        })
        .then(async function(response) {
          console.log("status: " + response.status)
          console.log("xcsrftoken: " + response.headers.get('X-CSRF-TOKEN'));
          console.log("setting xcsrfToken")
          await AsyncStorage.setItem('xcsrfToken', response.headers.get('X-CSRF-TOKEN'));

          var token = await AsyncStorage.getItem('xcsrfToken');

          var headers = {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            Cookie: cookie,
            'X-CSRF-TOKEN': token
          }

          var params = {
            eventId: nav.state.e_id,
            eventName: nav.state.e_name,
            eventStart: startDate,
            eventEnd: endDate,
            description: nav.state.e_description,
            location: nav.state.e_location
          }

          console.log("headers: " + JSON.stringify(headers))
          console.log("params: " + JSON.stringify(params))

          fetch(url, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(params)
          })
          .then(async function(response) {
            console.log("status: " + response.status)
            console.log("xcsrftoken: " + response.headers.get('X-CSRF-TOKEN'));
            if (response.ok) {
              console.log("setting xcsrfToken")
              await AsyncStorage.setItem('xcsrfToken', response.headers.get('X-CSRF-TOKEN'));
            }

            return response.json();
          })
          .then(function(json) {
            console.log("json.responseStatus: " + json.responseStatus)
            console.log("json.errorMessage: " + json.errorMessage)
            if (json.responseStatus === "OK") {
              nav.pop();
            } else {
              Alert.alert(json.errorMessage);
            }
          })
          .catch((error) => {
            Alert.alert("There was an error updating your event")
            console.log(error);
          });
        })
        .catch((error) => {
          Alert.alert("There was an error updating your event")
          console.log(error);
        });
        //navigator.parentNavigator.pop();
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
        backgroundColor: '#B20000',
        padding: 20,
        alignItems: 'center',
        marginTop: 50
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