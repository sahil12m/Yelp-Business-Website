import React from 'react'
import './Modal.css';
import { useState, useEffect } from 'react';
import { useLocalStorage } from "../useLocalStorage";



export default function Modal(modalres) {
    console.log(modalres.modalres);

    let bus_name = '';
    if(modalres.modalres != null && modalres.modalres != undefined){
        bus_name = modalres.modalres;
    }
    const [isSub, setIsSub] = useState(false);
    const [femail, setfemail] = useState('');
    const [fdate, setfdate] = useState('');
    const [ftime1, setftime1] = useState('');
    const [ftime2, setftime2] = useState('');
    const [busname, setbusname] = useLocalStorage('');
    const [fErremail, setfErremail] = useState('');
    const [fErrdate, setfErrdate] = useState('');
    const [fErrtime1, setfErrtime1] = useState('');
    const [fErrtime2, setfErrtime2] = useState('');

    useEffect(() => {
        // storing input name
        localStorage.setItem("email", JSON.stringify(femail));
      }, [femail]);

    
    useEffect(() => {
    // storing input name
    localStorage.setItem("name", JSON.stringify(busname));
    }, [busname]);

    useEffect(() => {
    // storing input name
    localStorage.setItem("date", JSON.stringify(fdate));
    }, [fdate]);

    useEffect(() => {
        // storing input name
        localStorage.setItem("time1", JSON.stringify(ftime1));
    }, [ftime1]);

    useEffect(() => {
        // storing input name
        localStorage.setItem("time2", JSON.stringify(ftime2));
    }, [ftime2]);



    const email = document.getElementById("validationCustomemail");
    const date = document.getElementById("validationCustomdate");

    const onChangeEmail = (e) => {
        setfErremail(valEm());
        setfemail(e.target.value);
        
      };

      const onChangeDate = (e) => {
        setfErrdate(valdate());
        setfdate(e.target.value);
        
      };

      const onChangeTime1 = (e) => {
        setfErrtime1(valtime1());
        setftime1(e.target.value);
        
      };

      const onChangeTime2 = (e) => {
        setfErrtime2(valtime2());
        setftime2(e.target.value);   
      };

      useEffect(() => {
        console.log(fErremail);
        if (Object.keys(fErremail).length === 0 && isSub) {
          console.log(femail);
        }
      }, [fErremail]);

      useEffect(() => {
        console.log(fErrtime1);
        if (Object.keys(fErrtime1).length === 0 && isSub) {
          console.log(ftime1);
        }
      }, [fErrtime1]);

      useEffect(() => {
        console.log(fErrtime2);
        if (Object.keys(fErrtime2).length === 0 && isSub) {
          console.log(ftime2);
        }
      }, [fErrtime2]);


      useEffect(() => {
        console.log(fErrdate);
        if (Object.keys(fErrdate).length === 0 && isSub) {
          console.log(fdate);
        }
      }, [fErrdate]);

    

    const handleSubmit = (e) => {
        e.preventDefault();
        setfErremail(valEm());
        setfErrdate(valdate());
        setIsSub(true);
        setfErrtime1(valtime1());
        setfErrtime2(valtime2());
        setbusname(bus_name);
        let emailerr = ''
        emailerr = valEm(); 
        if(!emailerr && fdate && ftime1 && ftime2){
        alert("Reservation created!");
        }
    };

    const valEm = () => {
        let errors = '';
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
        if (!femail) {
          errors = "Email is required";
          setErrorFor(email)
        } else if (!regex.test(femail)) {
          errors = "Email must be a valid email address";
          setErrorFor(email)
        }
        else{
            errors = '';
            setSuccessFor(email)
        }
        return errors;
      };


      const valdate = () => {
        let errors = '';
        if (!fdate) {
          errors = "Date is required";
          setErrorFor(date)
        } 
        else if(fdate){
            errors = '';
            setSuccessFor(date)
        }
    
        return errors;
      };

      const valtime1 = () => {
        let errors = '';
        if (!ftime1) {
          errors = "Time1 is required";
        } 
        else if(ftime1){
            errors = '';
        }
    
        return errors;
      };

      const valtime2 = () => {
        let errors = '';
        if (!ftime2) {
          errors = "Time2 is required";
        } 
        else if(ftime2){
            errors = '';
        }
    
        return errors;
      };

      function setErrorFor(input) {
        const formControl = input.parentElement; // .form-control div
      
        //add error class
        formControl.className = "form-control error";
      }

      function setErrorForsel(input) {
        const formSelect = input.parentElement; // .form-control div
      
        //add error class
        formSelect.className = "error";
      }

      function setSuccessFor(input) {
        const formControl = input.parentElement; // .form-control div
      
        //add error class
        formControl.className = "form-control success";
      }

      function setSuccessForsel(input) {
        const formSelect = input.parentElement; // .form-control div
      
        //add error class
        formSelect.className = "success";
      }
    

  return (
    <>
  <div className="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div className="modal-dialog modal-dialog-centered">
    <div className="modal-content">
      <div className="modal-header">
        <h1 className="modal-title fs-5" id="staticBackdropLabel">Reservation Form</h1>
      </div>
      <div className="modal-body">
        <div className='container'>
        <span className='text1'><b>{bus_name}</b></span>
        <form className="row needs-validation" onSubmit={handleSubmit} noValidate>
            <div className="row-md-4 modspace">
                <label className="form-label emaillabel" htmlFor="inputEmail4">Email</label>
                <div className="form-control input-group has-validation">
                    <input type="email" name='email' className="" id="validationCustomemail" value={femail} onChange={onChangeEmail} required/>
                    <i class="fas fa-exclamation-circle"></i>
                </div>
                <p className="inv">
                        {fErremail}
                </p>
            </div>
            <div className="row-md-4 modspace">
                <label className="form-label datelabel" htmlFor="inputEmail5">Date</label>
                <div className="form-control input-group has-validation">
                    <input type="date" name='date' className="" id="validationCustomdate" 
                    min={new Date().toISOString().slice(0,10)} value={fdate} onChange={onChangeDate} required/>
                    <i class="fas fa-exclamation-circle ex2"></i>
                </div>
                <p className="inv">
                        {fErrdate}
                </p>
            </div>
            <div className="row-md-4 modspace">
                    <label className="form-label timelabel" htmlFor="inputEmail6">Time</label>
                    <div className="input-group has-validation icon1">
                        <select name='time1' className='form-select select12' id="validationCustomtime1" value={ftime1} onChange = {onChangeTime1} required>
                            <option selected disabled hidden></option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                        </select>
                        <p className='colontime'>:</p>
                        <select name='time2' className="form-select sel2" value={ftime2} onChange = {onChangeTime2}  id="validationCustomtime2" required>
                            <option selected disabled hidden></option>
                            <option value="00">00</option>
                            <option value="15">15</option>
                            <option value="30">30</option>
                            <option value="45">45</option>
                        </select>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-clock clockicon" viewBox="0 0 16 16">
                        <path d="M8 3.5a.5.5 0 0 0-1 0V9a.5.5 0 0 0 .252.434l3.5 2a.5.5 0 0 0 .496-.868L8 8.71V3.5z"/>
                        <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm7-8A7 7 0 1 1 1 8a7 7 0 0 1 14 0z"/>
                        </svg>
                        <div className="invalid-feedback">
                        </div>
                    </div>
            </div>
            <div className='container button4'>
                <button className="btn btn-danger text-center" type="submit" onClick={handleSubmit}  >Submit</button>
            </div>
        </form>
        </div>
      </div>
      <div className="modal-footer">
        <button type="button" className="btn btn-dark" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
    </>
  )
}
