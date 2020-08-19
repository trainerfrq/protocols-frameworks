import React from "react";

export default class UnitSearchBar extends React.Component{
  constructor(props){
    super(props)
    console.log(props)
    props.ws.onmessage = (message) => {
      console.log(message)
      this.setState({
        result: message.data
      });
    }
    this.state={
      ws:props.ws,
      searchContent:"",
      result:""
    }

    this.handleChange=this.handleChange.bind(this)
    this.handleSearch=this.handleSearch.bind(this)
  }

  handleChange(event) {
    let value = event.target.value
    this.setState({
      searchContent: value
    })
  }

  handleSearch() {
    this.state.ws.send("convert|" + this.state.searchContent)
  }
  render(){
    return(
    <div>
        <input
          type={"text"}
          value={this.state.searchContent}
          onChange={this.handleChange}
        />
        <button onClick={this.handleSearch}>Search</button>
        <div>
          <h1>{this.state.result}</h1>
        </div>
    </div>
    )
  }
}