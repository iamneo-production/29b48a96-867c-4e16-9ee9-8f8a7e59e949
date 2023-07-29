#!/bin/bash
if [ -d "/home/coder/project/workspace/springapp/" ]
then
    echo "project folder present"
    # checking for src folder
    if [ -d "/home/coder/project/workspace/springapp/src/" ]
    then
        cp -r /home/coder/project/workspace/junit/test /home/coder/project/workspace/springapp/src/;
    cd /home/coder/project/workspace/springapp/ || exit;
    mvn clean test;
    else
        echo "testGetAppointmentAll FAILED";
        echo "testDeleteAppointmentById FAILED";
        echo "testGetAllBilling FAILED";
        echo "testUpdateInventory FAILED";
        echo "testGetPatientAll FAILED";
        echo "testCreatePatient FAILED";
        echo "testGetStaffById FAILED";
        echo "testCreateStaff FAILED";
        echo "testGetMedicalRecordById FAILED";
        echo "testGetPharmacyAll FAILED";
    fi
else   
         echo "testGetAppointmentAll FAILED";
        echo "testDeleteAppointmentById FAILED";
        echo "testGetAllBilling FAILED";
        echo "testUpdateInventory FAILED";
        echo "testGetPatientAll FAILED";
        echo "testCreatePatient FAILED";
        echo "testGetStaffById FAILED";
        echo "testCreateStaff FAILED";
        echo "testGetMedicalRecordById FAILED";
        echo "testGetPharmacyAll FAILED";
fi