//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2009 Openbravo, S.L.
//    http://www.openbravo.com/product/pos
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.data.gui;

import com.openbravo.data.gui.JImageEditor.ZoomIcon;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author adrianromero
 * @author Andrey Svininykh <svininykh@gmail.com>
 * @version NORD POS 3
 */
public class JImageViewer extends javax.swing.JPanel {
    
    private Dimension m_maxsize;
    private final ZoomIcon m_icon;
    private BufferedImage m_Img = null;
    
    public JImageViewer() {
        initComponents();        
        m_Img = null;
        m_maxsize = null;
        m_icon = new ZoomIcon();
        m_jImage.setIcon(m_icon);
        privateSetEnabled(isEnabled());
    }
    
    public void setMaxDimensions(Dimension size) {
        m_maxsize = size;
    }
    public Dimension getMaxDimensions() {
        return m_maxsize;
    }
    
    @Override
    public void setEnabled(boolean value) {
        privateSetEnabled(value);
        super.setEnabled(value);
    }
    
    private void privateSetEnabled(boolean value) {
        m_jScr.setEnabled(value && (m_Img != null));
    }
    
    public void setImage(BufferedImage img) {
        BufferedImage oldimg = m_Img;
        m_Img = img;
        m_icon.setIcon(m_Img == null ? null : new ImageIcon(m_Img));
     
        m_jImage.revalidate();
        m_jScr.revalidate();
        m_jScr.repaint();

        privateSetEnabled(isEnabled());
        
        firePropertyChange("image", oldimg, m_Img);
    }
    
    public BufferedImage getImage() {
        return m_Img;
    }    
    
    public double getZoom() {
        return m_icon.getZoom();
    }
 
    public void setZoom(double zoom) {
        double oldzoom = m_icon.getZoom();
        m_icon.setZoom(zoom);
        
        m_jImage.revalidate();
        m_jScr.revalidate();
        m_jScr.repaint();
        
        firePropertyChange("zoom", oldzoom, zoom);
    }
    
    public void incZoom() {        
        double zoom = m_icon.getZoom();
        setZoom(zoom > 4.0 ? 8.0 : zoom * 2.0);
    }
    
    public void decZoom() {        
        double zoom = m_icon.getZoom();
        setZoom(zoom < 0.5 ? 0.25 : zoom / 2.0);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_jScr = new javax.swing.JScrollPane();
        m_jImage = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        m_jImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_jImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        m_jScr.setViewportView(m_jImage);

        add(m_jScr, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel m_jImage;
    private javax.swing.JScrollPane m_jScr;
    // End of variables declaration//GEN-END:variables
    
}
