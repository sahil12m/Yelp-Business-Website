import React from 'react';
import './Form.css';
import axios from 'axios';
import { useState } from 'react';
import Table from './Table';
import Tabs from './Tabs';


export default function Form() {

    const [keys, setkeys] = useState('');
    const [drop, setdrop] = useState([]);
    const [loc, setLoc] = useState('');
    const [dist, setdist] = useState(10);
    const [cat, setcat] = useState('all')
    const [check, setCheck] = useState(false);
    const [res, setres] = useState([]);
    const [disptab, setdisptab] = useState(false);
    const [dispcard, setdispcard] = useState(false);
    const [dispnores, setdispnores] = useState(false);
    const [details, setDetails] = useState([]);
    const [busreviews, setbusrev] = useState([]);
    const [emptytext, setemptytext] = useState('');

    const onChangeHandler = (event) => {
        setkeys(event.target.value);
        loadsuggestion(event.target.value);
    }

    const locChangeHandler = (event) => {
        setLoc(event.target.value);
    }

    // const keyChangeHandler = (event) => {
    //     setkeys(event.target.value);
    // }

    const distChangeHandler = (event) => {
        setdist(event.target.value);
    }

    const catChangeHandler = (event) => {
        setcat(event.target.value);
        // console.log(cat);
    }

    const onSuggestHandler = (suggest) => {
        setkeys(suggest);
        setdrop([]);
    }

    const onClickHandler = (e) => {
        e.preventDefault()
        loadtable();
    }

    const onClearHandler = (e) => {
        e.preventDefault()
        setkeys('');
        setdist(10);
        setcat('all');
        setLoc('');
        if(check){
            setCheck(!check);
        }
        setdisptab(false);
        setdispcard(false);
        setdispnores(false);

    }

    const loadtable = async () => {
        if(check){
            const response = await axios.get('https://ipinfo.io/?token=4ab812bd3b5835');
            if (response.status === 200) {
                let res = response.data;
                console.log(response.data);
                let lat = res['loc'].split(/[,]/)[0];
                let lng = res['loc'].split(/[,]/)[1];
                const response_ser = await axios.get(`https://homework8-react.wl.r.appspot.com/search?keyword=${keys}&latitude=${lat}&longitude=${lng}&categories=${cat}&radius=${dist}`);
                let bus_res = response_ser.data.businesses;
                if(bus_res.length == 0){
                    setemptytext('No results available');
                    setdispnores(true);
                    setdisptab(false);
                    setdispcard(false);
                }
                else{
                setres(bus_res);
                setemptytext('');
                setdisptab(true);
                setdispcard(false);
                setdispnores(!dispnores);
                }
            }
        }
        else{
            const geo_api = "AIzaSyBLZmOejZR198CAWqDNlk87ZLcSxfRL8Ys";
            const response = await axios.get(`https://maps.googleapis.com/maps/api/geocode/json?address=${loc}&key=${geo_api}`);
            console.log(response.data);
            if (response.status === 200) {
                let res = response.data;
                let lat = res.results['0']['geometry']['location']['lat'];
                let lng = res.results['0']['geometry']['location']['lng'];
                const response_ser = await axios.get(`https://homework8-react.wl.r.appspot.com/search?keyword=${keys}&latitude=${lat}&longitude=${lng}&categories=${cat}&radius=${dist}`);
                console.log(response_ser.data);
                let bus_res = response_ser.data.businesses;
                if(bus_res.length == 0){
                    setemptytext('No results available');
                    setdispnores(true);
                    setdisptab(false);
                    setdispcard(false);
                }
                else{
                setres(bus_res);
                setemptytext('');
                setdisptab(true);
                setdispcard(false);
                setdispnores(!dispnores);
                }
            }
        }
    }

    const loadsuggestion = async (key) => {
        const response = await axios.get(`https://homework8-react.wl.r.appspot.com/suggestion?keyword=${key}`);
        console.log(response.data.data)
        let suggestion_arr = [];
        let terms = response.data.terms;
        for(let x=0; x < terms.length; x++)
            suggestion_arr.push(terms[x]['text'])

        let cat = response.data.categories;
        for(let x=0; x < cat.length; x++)
            suggestion_arr.push(cat[x]['title'])    

        setdrop(suggestion_arr);
    }

  return (
    <>
    <div className='container mainform'>
    <span className='text1'><b>Business Search</b></span>
    <form className="form-horizontal" onSubmit={onClickHandler}>
        <div className="row space1">
            <div className="form-group col space2">
            <label className="label"htmlFor="inputEmail4">Keyword</label>
            <input type="text" className="form-control space2" id="inputEmail4" onChange = {onChangeHandler} value={keys} onBlur={() => setdrop([])} required = {true}/>
            {
                drop && drop.map(
                    (drop, x) => <div key={x.toString()} className="suggestion col justify-content-sm-center" onMouseDown={() =>{onSuggestHandler(drop)}}>{drop}</div>
                )
            }
            </div>
        </div>
        <div className="row space1">
            <div className="form-group col-sm-7 ">
            <label htmlFor="inputCity">Distance</label>
            <input type="text" className="form-control space2" id="inputCity" onChange = {distChangeHandler} value={dist}placeholder="10"/>
            </div>
            <div className="form-group col-sm-4">
            <label className="label" htmlFor="inputState">Category</label>
            <select id="inputState" className="form-select space2" onChange = {catChangeHandler} value={cat}>
                <option value='all' onChange = {catChangeHandler}>Default</option>
                <option value='arts' onChange = {catChangeHandler}>Arts & Entertainment</option>
                <option value='health' onChange = {catChangeHandler}>Health & Medical</option>
                <option value='hotelstravel' onChange = {catChangeHandler}>Hotels & Travel</option>
                <option value='food' onChange = {catChangeHandler}>Food</option>
                <option value='professional' onChange = {catChangeHandler}>Professional Services</option>
            </select>
            </div>
        </div>
        <div className="form-group col space1">
                <label className="label" htmlFor="inputCity">Location</label>
                <input type="text" className="form-control space2" id="inputCity" disabled={check}
         value={loc} onChange = {locChangeHandler} required = {true}/>
        </div>
        <div className="form-group space1">
            <div className="form-check">
            <input className="form-check-input" type="checkbox" id="gridCheck" checked={check}
            onChange={() => {
                if(!check){
                    setLoc('')
                  }
              setCheck(!check)
                }
           }/>
            <label className="form-check-label checkbox1" htmlFor="gridCheck">
                Auto-detect my location
            </label>
            </div>
        </div>
        <div className = "row">
            <div className = "col text-center">
            <button type="submit" className="btn btn-danger button1">Submit</button>
            <button type="clear" className="btn btn-primary" onClick={onClearHandler}>Clear</button>
            </div>
        </div>
    </form>
    </div>
    {dispnores && <div className='container text-center nores'>
      <p className='emptyres'>{emptytext}</p>
    </div>}
    <div className='container'>
    {disptab && <Table bus_res = {res} setDetails = {setDetails} setbusrev = {setbusrev} setdisptab = {setdisptab} setdispcard = {setdispcard}></Table>
    }
    {dispcard &&
    <Tabs details={details} busreviews={busreviews} setdisptab = {setdisptab} setdispcard = {setdispcard}/>
    }
    </div>
    </>
  )
}
