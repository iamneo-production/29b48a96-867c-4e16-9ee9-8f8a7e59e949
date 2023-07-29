import React, { useEffect, useState } from "react";

import "./Inventory.css";
import { InventoryForm } from "./InventoryForm";
import ListInventories from "./ListInventories";
import { helpers } from "./InventoryHelper";

const Inventory = () => {
  const [formData, setFormData] = useState({
    id: 0,
    name: "",
    quantity: "",
    category: "",
    price: "",
    supplier: "",
  });

  const [inventories, setList] = useState([]);

  const handleFormValueChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();
    if (formData.id == 0) {
      helpers
        .addNewInventory(formData)
        .then((response) => {
          onFinishSumbitAndUpdate();
        })
        .catch((error) => {
          console.error(error);
        });
    } else {
      helpers
        .updateInventory(formData)
        .then((response) => {
          onFinishSumbitAndUpdate();
        })
        .catch((error) => {
          console.error(error);
        });
    }
  };

  useEffect(() => {
    helpers
      .getAllInventory()
      .then((data) => {
        setList(data.data);
      })
      .catch((error) => console.log(error));
  }, []);

  const refresh = () => {
    helpers
      .getAllInventory()
      .then((data) => {
        setList(data.data);
      })
      .catch((error) => console.log(error));
  };

  const handleUpdate = (inventory) => {
    setFormData({
      id: inventory.id,
      name: inventory.name,
      supplier: inventory.supplier,
      category: inventory.category,
      price: inventory.price,
      quantity: inventory.quantity,
    });
  };

  const handleDelete = (id) => {
    helpers.delteInventory(id).then((data) => {
      if (data.data == "Deleted Successfully") {
        refresh();
      }
    });
  };

  const onFinishSumbitAndUpdate = () => {
    setFormData({
      id: 0,
      name: "",
      quantity: "",
      category: "",
      price: "",
      supplier: "",
    });
    refresh();
  };

  return (
    <div className="root vh-100 vw-100 p-3 d-flex overflow-hidden">
      <div className="col-4 h-75 align-self-center inventory-form">
        <h2 className="form-header">
          {formData.id == 0 ? "Add new " : "Updating "}Inventory
        </h2>
        <InventoryForm
          handleFormSubmit={handleFormSubmit}
          formData={formData}
          handleFormValueChange={handleFormValueChange}
        />
      </div>
      <div className="col-8">
        <ListInventories
          inventories={inventories}
          handleDelete={handleDelete}
          handleUpdate={handleUpdate}
        />
      </div>
    </div>
  );
};

export default Inventory;
