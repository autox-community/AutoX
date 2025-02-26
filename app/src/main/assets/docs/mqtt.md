# MQTT

`6.5.9 新增`

> 稳定性: 稳定

MQTT 模块，采用[org.eclipse.paho.client.mqttv3](https://github.com/eclipse/paho.mqtt.java)实现

## 代码示例

> 主题订阅、发布、QOS、遗嘱消息

```js
importPackage(Packages["org.eclipse.paho.client.mqttv3"]);
importClass("org.eclipse.paho.android.service.MqttAndroidClient");

// 连接、订阅配置
const MQTT_URL = "tcp://192.168.20.225:1883";
const CLIENT_ID = "MOCK";
const TOPIC = "ANDROID_MOCK";
const QOS = 2;
const USERNAME = "device";
const PASSWORD = "public";

const client = new MqttAndroidClient(context, MQTT_URL, CLIENT_ID);
const subscribeToTopic = () => {
  try {
    client.subscribe(
      TOPIC,
      QOS,
      null,
      new IMqttActionListener({
        onSuccess: (token) => {
          toast("MQTT 订阅成功");
        },
        onFailure: (token, error) => {
          toast("MQTT 订阅失败 " + error);
        },
      })
    );
  } catch (error) {
    toast(error.message);
    alert('MQTT订阅错误\n\n"' + error.message);
  }
};

const initMQTT = () => {
  // 创建配置
  const mqttConnectOptions = new MqttConnectOptions();
  mqttConnectOptions.setAutomaticReconnect(true);
  mqttConnectOptions.setCleanSession(true);
  mqttConnectOptions.setUserName(USERNAME);
  mqttConnectOptions.setPassword(Array.from(PASSWORD));
  // 遗嘱消息 QOS = 1, retained = true
  let willMsgJavaString = new java.lang.String("i am gone");
  let willMsgJavaBytes = willMsgJavaString.getBytes("UTF-8");
  mqttConnectOptions.setWill("device-gone", willMsgJavaBytes, 1, true);

  console.log("mqttConnectOptions", mqttConnectOptions);

  const callback = new MqttCallbackExtended({
    connectComplete: (reconnect, serverUri) => {
      if (reconnect) {
        subscribeToTopic();
        console.log("重新连接到MQTT");
      } else {
        console.log("连接到MQTT");
      }
    },
    connectionLost: () => {
      console.log("MQTT 连接丢失");
    },
    messageArrived: (topic, message) => {
      console.log("MQTT MESSAGE: ", topic, message);
    },
  });
  client.setCallback(callback);

  client.connect(
    mqttConnectOptions,
    null,
    new IMqttActionListener({
      onSuccess: () => {
        console.log("mqtt连接成功");
        subscribeToTopic();
      },
      onFailure: (token, error) => {
        console.error("mqtt连接失败", error);
        exit();
      },
    })
  );
};

const publish = (topic, msg, qos = 1, retained = false) => {
  // publish message
  try {
    let javaString = new java.lang.String(msg);
    let byteArray = javaString.getBytes("UTF-8");
    client.publish(topic, byteArray, qos, retained);
  } catch (error) {
    console.error("MQTT 发布失败", error);
  }
};

// 连接
initMQTT();
setTimeout(() => {
  toast("7秒后自动关闭");
  // send message
  publish(TOPIC, "hello");
}, 3000);
// 断开并退出
setTimeout(() => {
  client.close();
  client.disconnect();
  toast("自动关闭并退出脚本");
  exit();
}, 10 * 1000);

// 防止进程退出
setInterval(() => {
  //
}, 1000);
```
