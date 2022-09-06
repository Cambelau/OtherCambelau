/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentable.DAO;

import java.util.ArrayList;
import rentable.Location;

/**
 *
 * @author sienn
 */
public interface LocationDAO {
    
    void reserver(Location l) throws DAOException;
    ArrayList<Location> listeLocation() throws DAOException;
}
