cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "cordova-plugin-device.device",
      "file": "plugins/cordova-plugin-device/www/device.js",
      "pluginId": "cordova-plugin-device",
      "clobbers": [
        "device"
      ]
    },
    {
      "id": "cordova-plugin-rabbitmq.RabbitMQPlugin",
      "file": "plugins/cordova-plugin-rabbitmq/www/RabbitMQPlugin.js",
      "pluginId": "cordova-plugin-rabbitmq",
      "clobbers": [
        "RabbitMQ"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-whitelist": "1.3.4",
    "cordova-plugin-device": "2.0.3",
    "cordova-plugin-jcore": "1.2.8",
    "cordova-plugin-rabbitmq": "0.0.1"
  };
});