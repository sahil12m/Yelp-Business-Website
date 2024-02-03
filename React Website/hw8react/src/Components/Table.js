import React from 'react';
import './Table.css';
import axios from 'axios';

export default function Table({bus_res, setDetails, setbusrev, setdisptab, setdispcard}) {
    
    console.log(bus_res);
    const Formgen = async (id) => {
        console.log('Clicking....')
        const response = await axios.get(`https://homework8-react.wl.r.appspot.com/card?id=${id}`);
        if (response.status === 200) {
            let res = response.data;
            setDetails(res);
            setdisptab(false);
            setdispcard(true);
            // console.log(res);
        } else {
            console.log("Error");
        }
        const response_rev = await axios.get(`https://homework8-react.wl.r.appspot.com/review?id=${id}`);
        if (response_rev.status === 200) {
            let res_rev = response_rev.data.reviews;
            setbusrev(res_rev);
            setdisptab(false);
            setdispcard(true);
        } else {
            console.log("Error");
        }
    }
    return (
        <>
        <table className="container-fluid table table-striped tab">
        <thead>
          <tr>
            <th scope="col-sm">#</th>
            <th scope="col-sm">Image</th>
            <th scope="col-sm">Business Name</th>
            <th scope="col-sm">Rating</th>
            <th scope="col-sm">Distance(miles)</th>
          </tr>
        </thead>
        <tbody>
        {bus_res.map((x, i) => (
            <tr className='tableelement' key={i} onClick={()=>{Formgen(x.id);}}>
              <td>{i+1}</td>
              <td> 
                <img src={x.image_url} width="100" height="100" alt={'Image'+i} />
              </td>
              <td>{x.name}</td>
              <td>{x.rating}</td>
              <td>{(x.distance / 1609).toFixed(2)}</td>
            </tr>
          ))}
        </tbody>
      </table>
      {/* <Tabs tab_res = {cardres}></Tabs> */}
      </>
  )
}
