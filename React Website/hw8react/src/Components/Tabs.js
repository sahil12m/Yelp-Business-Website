import * as React from 'react';
import PropTypes from 'prop-types';
import Box from '@mui/material/Box';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import './Tabs.css';
import Modal from './Modal';
import GoMap from './Map';


function TabPanel(props) {
    const { children, value, index, ...other } = props;
  
    return (
      <div
        role="tabpanel"
        hidden={value !== index}
        id={`full-width-tabpanel-${index}`}
        aria-labelledby={`full-width-tab-${index}`}
        {...other}
      >
        {value === index && (
          <Box sx={{ p: 3 }}>
            <Typography>{children}</Typography>
          </Box>
        )}
      </div>
    );
  }
  
  TabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.number.isRequired,
    value: PropTypes.number.isRequired,
  };
  

export default function CenteredTabs({details, busreviews, setdisptab, setdispcard}) {
  const [value, setValue] = React.useState(0);

  console.log(details);
  console.log(busreviews);

  let mapin = {
  lat: details.coordinates.latitude,
  lng: details.coordinates.longitude
  }

  const onclhandler = () => {
    console.log('Clicking....')
    setdisptab(true);
    setdispcard(false);
}

  let rev_name = ''
  if(busreviews.user != undefined && busreviews.user != null){
      rev_name = busreviews['0']['user']['name'];
  }

  let address = ''
  if(details.location != undefined && details.location != null){
    if(details.location.display_address != undefined && details.location.display_address != null){
      address = details.location.display_address.join(' ');
    }
  }

  let phone = ''
  if(details != undefined && details != null){
    phone = details.display_phone;
  }

  let status = ''
  if(details.hours != undefined && details.hours != null){
    status = details.hours[0]['is_open_now'];
    if(status === true){
      status = "Open Now";
    }
    else{
      status = "Closed";
    }
  }

  const checkColor = (description) => {
    switch (description) {
      case "Closed":
        return "red";
      case "Open Now":
        return "green";
      default:
    }};

  let categories = []
  if(details.categories != undefined && details.categories != null){
    for(let i=0; i<details['categories'].length; i++){
      categories[i] = details['categories'][i]['title'];
    }
    categories = categories.join(' | ');
  }

  let price = ''
  if(details.price != undefined && details.price != null){
    price = details['price'];
  }

  let link = ''
  if(details.url != undefined && details.url != null){
    link = details['url'];
  }

  let name = details['name']

  let photos = [];
  if(details.photos != undefined && details.photos != null)
      for(let i=0; i<details['photos'].length; i++){
        photos[i] = details['photos'][i];
      }

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  
  return (
    <>
    <div className = 'bg-white card_dat'>
    <div className = 'header1'>
      <svg onClick={onclhandler} xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-arrow-left" viewBox="0 0 16 16">
      <path fillRule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
      </svg>
      <h3 className = 'text-center'>{name}</h3>
    </div>
    <Box sx={{ width: '100%', bgcolor: 'background.paper' }}>
      <Tabs className = 'tab_card' value={value} onChange={handleChange} centered>
        <Tab className='cardlink' label="Business Details" />
        <Tab className='cardlink' label="Map Location" />
        <Tab className='cardlink' label="Reviews" />
      </Tabs>
    </Box>
    <TabPanel className='container-fluid panel1' value={value} index={0}>
    {/* <div className="container-fluid card_res"> */}
        <div className="row">
            <div className="column col-6 text-center">
            <p className='headline'><b>Address</b></p>
            <p className="addr">{address}</p>  
            <p className='headline'><b>Phone</b></p>
            <p className="phn">{phone}</p>
            <p className='headline'><b>Status</b></p>
            <p className="stat" id='open' style={{ color: `${checkColor(status)}` }}>{status}</p>
            </div>
            <div className="column col-6 text-center">
            <p className='headline'><b>Category</b></p>
            <p className="cat">{categories}</p>
            <p className='headline'><b>Price Range</b></p>
            <p className="price">{price}</p>
            <p className='headline'><b>Visit yelp for more</b></p>
            <p><a href={link} target='_blank'rel="noreferrer">Business Link</a></p>
            </div>
            <div className='button2'>
            <button type="button" className="btn btn-danger modbut" data-bs-toggle='modal' data-bs-target='#staticBackdrop'>
                Reserve Now
            </button>
            </div>
            <div className='text-center icontwt'>
            <span className='span1'>Share on:</span>
            <a href={`https://twitter.com/intent/tweet?text=Check ${name} on Yelp. ${link}`} className='twtcolor' target = "_blank" rel="noreferrer">
            <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" className="bi bi-twitter " viewBox="0 0 16 16">
            <path d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"/>
          </svg>
          </a>
          <a href={`https://www.facebook.com/sharer/sharer.php?display=page&u= ${link}`} className='twtcolor' target = "_blank" rel="noreferrer">
          <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-facebook fabcolor" viewBox="0 0 16 16">
          <path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z"/>
        </svg>
          </a>
          </div>
            <div id="carouselExampleInterval" className="carousel slide" data-bs-ride="carousel">
              <div className="carousel-inner">
                <div className="carousel-item active" data-bs-interval="5000">
                  <img src={photos[0]} className="d-block w-100 cardcaro" alt="..."/>
                </div>
                <div className="carousel-item" data-bs-interval="2000">
                  <img src={photos[1]} className="d-block w-100 cardcaro" alt="..."/>
                </div>
                <div className="carousel-item">
                  <img src={photos[2]} className="d-block w-100 cardcaro" alt="..."/>
                </div>
              </div>
              <button className="carousel-control-prev butcar" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
                <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                <span className="visually-hidden">Previous</span>
              </button>
              <button className="carousel-control-next butcar" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
                <span className="carousel-control-next-icon" aria-hidden="true"></span>
                <span className="visually-hidden">Next</span>
              </button>
            </div>
        </div>
    </TabPanel>
    <TabPanel value={value} index={1}>
      <div className='container gmap'>
        <GoMap mapin = {mapin}/>
      </div>
    </TabPanel>
    <TabPanel value={value} index={2}>
    <table className="table table-striped tab">
        <tbody>
        {busreviews.map((x, i) => (
            <tr key={i}>
              <div>
              <p><b>{x.user.name}</b></p>
              <p> 
                Rating: {x.rating}/5
              </p>
              <p>{x.text}</p>
              <p>{x.time_created.split(' ')[0]}</p>
              </div>
            </tr>
          ))}
        </tbody>
      </table>
    </TabPanel>
    <Modal modalres = {details.name}/>
    </div>
    </>
  );
}