import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

const NavigationButtons = () => {
    const navigate = useNavigate();
    const location = useLocation();

    const goBack = () => {
        const pathSegments = location.pathname.split('/').filter(Boolean);

        if (pathSegments.length === 5 && pathSegments[4] === 'students') {
            // /locations/:locationName/groups/:groupName/students
            const locationName = pathSegments[1];
            navigate(`/locations/${locationName}/groups`);
        } else if (pathSegments.length === 1 && pathSegments[0] === 'students') {
            // /students
            navigate(`/locations`);
        } else if (pathSegments.length === 3 && pathSegments[2] === 'groups') {
            // /locations/:locationName/students
            navigate(`/locations`);
        }else {
            // Default fallback
            navigate(-1);
        }
    };


    const goHome = () => {
        navigate('/locations');
    };

    return (
        <div className="d-flex justify-content-between my-3">
            <button variant="secondary" onClick={goBack}>
                ← Back
            </button>
            <button variant="primary" onClick={goHome}>
                🏠 All Locations
            </button>
        </div>
    );
};

export default NavigationButtons;
