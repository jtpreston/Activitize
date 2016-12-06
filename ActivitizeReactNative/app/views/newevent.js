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
  TimePickerAndroid,
  AsyncStorage
} from 'react-native';

var CookieManager = require('react-native-cookies');

function utcDate(date, time) {
  var tokens = time.split(" ");
  var clock = tokens[0];
  var suffix = tokens[1];
  var hour = parseInt(clock.substring(0, clock.indexOf(':')));
  var minutes = clock.substring(clock.indexOf(':') + 1, clock.length);
  var day = date.getDate();
  var month = date.getMonth() + 1;
  var year = date.getFullYear();

  if (suffix === "p.m.") {
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

  var offset = date.getTimezoneOffset() / 60;

  var str = year + "-" + month + "-" + day + "T" + hourStr + ":" + minutes + ":00";
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

function getFormattedTime(hour, minute) {
  return (hour > 12 ? (hour - 12) : hour ) + ':' + (minute < 10 ? '0' + minute : minute) + (hour >= 12 ? ' p.m.' : ' a.m.');
}

export class NewEvent extends React.Component{
  constructor(props) {
    super(props);
    this.state = {};

  }

  async componentWillMount() {
    await this.props.navigator.setState({
      eventName: '',
      date: new Date(),
      date2: '',
      dateEnd: '',
      time: 'Start Time',
      invite: '',
      dateText: 'Start Date',
      time2: 'End Time',
      dateText2: 'End Date',
      hour: 12,
      minute: 0,
      description: '',
      location: ''
    });
    this.forceUpdate();
  }

  getCookie(url, callback) {
    CookieManager.get(url, (err, res) => {
      console.log('Got cookies for url ', res);
      var cookie = 'JSESSIONID=' + res.JSESSIONID;
      callback(cookie);
    });
  }

  getRememberMe(url, callback) {
    CookieManager.get(url, (err, res) => {
      console.log('Got cookies for url ', res);
      var remember = 'remember-me=' + res['remember-me'];
      callback(remember);
    });
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
      <View style={styles.container}>
            <View style={styles.inputs}>
                <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input]}
                        placeholder="Event Name"
                        onChangeText={(eventName) => this.props.navigator.setState({eventName})}
                        value={this.props.navigator.state.eventName}
                    />
                    </View>
                    <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input]}
                        placeholder="Location"
                        onChangeText={(location) => this.props.navigator.setState({location})}
                        value={this.props.navigator.state.location}
                    />
                    </View>
                    <View style={styles.inputContainer}>
                    <TextInput 
                        style={[styles.input]}
                        multiline={true}
                        maxLength={50}
                        numberOfLines={4}
                        placeholder="Description"
                        onChangeText={(description) => this.props.navigator.setState({description})}
                        value={this.props.navigator.state.description}
                    />
                </View>
                  <TouchableWithoutFeedback
                    onPress={this.showPicker.bind(this, {date: this.props.navigator.state.date, minDate: new Date()})}>
                    <View style={styles.inputContainer}>
                    <Text style={styles.date}>{this.props.navigator.state.dateText}</Text>
                    </View>
                    </TouchableWithoutFeedback>
          <TouchableWithoutFeedback
            onPress={this.showTimePicker.bind(this, {
              hour: this.props.navigator.state.hour,
              minute: this.props.navigator.state.minute,
            })}>
            <View style={styles.inputContainer}>
                    <Text style={styles.date}>{this.props.navigator.state.time}</Text>
                    </View>
          </TouchableWithoutFeedback>
          <TouchableWithoutFeedback
                    onPress={this.showPicker2.bind(this, {date: this.props.navigator.state.date2 || this.props.navigator.state.date, minDate: this.props.navigator.state.date})}>
                    <View style={styles.inputContainer}>
                    <Text style={styles.date}>{this.props.navigator.state.dateText2}</Text>
                    </View>
                    </TouchableWithoutFeedback>
          <TouchableWithoutFeedback
            onPress={this.showTimePicker2.bind(this, {
              hour: this.props.navigator.state.hour,
              minute: this.props.navigator.state.minute,
            })}>
            <View style={styles.inputContainer}>
                    <Text style={styles.date}>{this.props.navigator.state.time2}</Text>
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
        this.props.navigator.setState({date: new Date(year, month, day)});
        //newState[stateKey + 'Text'] = date.toLocaleDateString();
        var dateString = (month + 1) + "/" + day + "/" + year;
        this.props.navigator.setState({dateText: dateString});
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
        this.props.navigator.setState({date2: new Date(year, month, day)});
        //newState[stateKey + 'Text'] = date.toLocaleDateString();
        var dateString = (month + 1) + "/" + day + "/" + year;
        this.props.navigator.setState({dateText2: dateString});
      }
    } catch ({code, message}) {
      console.warn('Error ', message);
    }
  };

  showTimePicker = async (options) => {
    try {
      const {action, minute, hour} = await TimePickerAndroid.open(options);
      if (action === TimePickerAndroid.timeSetAction) {
        this.props.navigator.setState({time: getFormattedTime(hour, minute)});
        this.props.navigator.setState({hour: hour});
        this.props.navigator.setState({minute: minute});
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
        this.props.navigator.setState({time2: getFormattedTime(hour, minute)});
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
      onPress={async () => {
        var startDate = utcDate(navigator.parentNavigator.state.date, navigator.parentNavigator.state.time);
        var endDate = utcDate(navigator.parentNavigator.state.date2, navigator.parentNavigator.state.time2);
        console.log("date: " + startDate)
        console.log("date2: " + endDate)

        var nav = navigator.parentNavigator;
        var url = 'https://activitize.net/activitize/events/createEvent';
        var cookie = await AsyncStorage.getItem('jsessionid');
        var token = await AsyncStorage.getItem('xcsrfToken');

        var headers = {
          Cookie: cookie,
          'X-CSRF-TOKEN': token
        }

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
            eventName: nav.state.eventName,
            eventStart: startDate,
            eventEnd: endDate,
            description: nav.state.description,
            location: nav.state.location,
            priv: 1,
            numberOfComments: 0,
            subevent: 0
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
          console.log("json.responseStatus: " + json.responseStatus);
          if (json.responseStatus === "OK") {
            nav.pop();
          } else {
            Alert.alert(json.errorMessage);
          }
          })
          .catch((error) => {
            console.error(error);
          });
        })
        //nav.pop();
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
        borderBottomColor: '#1A1423',
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