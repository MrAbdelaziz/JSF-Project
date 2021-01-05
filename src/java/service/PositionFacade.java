/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Position;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Coder
 */
@Stateless
public class PositionFacade extends AbstractFacade<Position> {
    @PersistenceContext(unitName = "JSFprojectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PositionFacade() {
        super(Position.class);
    }
    
}
