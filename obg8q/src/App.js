import React from "react";
import "./styles.css";
import UnitSearchBar from "./Components/UnitSearchBar"

export default function App() {
  let webSocket;
  let a = () => {
    webSocket = new WebSocket("ws://localhost:8099/camel/hello");
    /*setTimeout(() => {
      webSocket.send("message|Hello, from the other side.");
    },5000);

    setInterval(() => {
      webSocket.send("heartbeat|" + new Date().getDate());
    },1000);*/

    webSocket.onmessage = (message) => console.log("NEW MESSAGE: " + message);
  };

  a();

  return (
    <div className="App">
      <h1>Hello.</h1>
      <UnitSearchBar ws = {webSocket}/>
    </div>
  );
}
