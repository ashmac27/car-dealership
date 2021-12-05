package data;

import java.util.List;
import model.Purchase;

public interface PurchaseDAO {
    
    /**
     * Adds a purchase to the DAO
     * @param purchase The purchase to be added
     * @return Purchase with generated fields, null if there's a problem
     */
    public Purchase add(Purchase purchase);
    
    /**
     * Gets purchase by ID
     * @param purchaseId ID of purchase
     * @return Purchase if found, null if not found
     */
    public Purchase getPurchaseByPurchaseId(int purchaseId);
    
    /**
     * Gets a list of all purchases
     * @return list of all purchases
     */
    public List<Purchase> getAllPurchases();
    
    /**
     * Gets a purchase based on the Vehicle ID (VIN)
     * @param vehicleId VIN
     * @return This vehicle's purchase, null if not found
     */
    public Purchase getPurchaseByVehicleId(String vehicleId);
    
    /**
     * Gets a list of all purchases under a salesperson
     * @param salespersonId ID of salesperson
     * @return List of purchases, empty if there are none
     */
    public List<Purchase> getPurchasesBySalespersonId(int salespersonId);
    
    /**
     * Updates purchase
     * @param purchase purchase to be updated
     * @return True if found and updated
     */
    public boolean update(Purchase purchase);
    
    /**
     * Deletes purchase
     * @param purchaseId ID of purchase to be deleted
     * @return True if found and deleted
     */
    public boolean deleteByPurchaseId(int purchaseId);
}