import React from 'react'
import { Marker, GoogleMap, LoadScript, useJsApiLoader } from '@react-google-maps/api';

const mapcont = {
  width: '1200px',
  height: '700px'
};


// function GoMap({mapin}) {
 

//   const {isLoaded, loadError} = useJsApiLoader({
//     // id: 'google-map-script',
//     googleMapsApiKey: "AIzaSyByb0of6GedOAUNpaZuq-q3DwhrCMyrAwo"
//   })
  
//     const renderMap = () => {
//       // wrapping to a function is useful in case you want to access `window.google`
//       // to eg. setup options or create latLng object, it won't be available otherwise
//       // feel free to render directly if you don't need that
    
//       return <GoogleMap
//         mapContainerStyle={containerStyle}
//         center={mapin}
//         zoom={10}
//       >
//         {
//           // ...Your map components
//         }
//       </GoogleMap>
//     }
  
//     if (loadError) {
//       return <div>Map cannot be loaded right now, sorry.</div>
//     }
  
//     return isLoaded ? renderMap() : <div/>
//   }

//   // return isLoaded ? (
//   //     <GoogleMap
//   //       mapContainerStyle={containerStyle}
//   //       center={mapin}
//   //       zoom={10}
//   //     >
//   //       { /* Child components, such as markers, info windows, etc. */ }
//   //       <Marker position={mapin}></Marker>
//   //       <></>
//   //     </GoogleMap>
//   // ) : <></>
// // }

// export default React.memo(GoMap);


function GoMap({mapin}) {
  console.log(mapin)
  return (
    <LoadScript
      googleMapsApiKey="AIzaSyBLZmOejZR198CAWqDNlk87ZLcSxfRL8Ys"
    >
      <GoogleMap
        zoom={12}
        mapContainerStyle={mapcont}
        center={mapin}
        
      >
        { <Marker position={mapin}></Marker> }
        
        <></>
      </GoogleMap>
    </LoadScript>
  )
}

export default React.memo(GoMap)