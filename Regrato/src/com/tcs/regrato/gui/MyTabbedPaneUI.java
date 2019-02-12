/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 *
 * @author 1299792
 */
public class MyTabbedPaneUI extends BasicTabbedPaneUI {
  
  private static final int ADDED_TAB_HEIGTH = 10;
  private static final int ADDED_TAB_WIDTH = 10;
  private static final int SPACE_BETWEEN_TAB = -1;
//  private static final Color TAB_COLOR = Color.GRAY;
  private static final Color TAB_COLOR = new Color(75,75,75);
//  private static final Color SELECTED_TAB_COLOR = Color.BLUE;
  private static final Color SELECTED_TAB_COLOR = new Color(0,0,0);
  
 
  public static ComponentUI createUI(JComponent c) {
    return new MyTabbedPaneUI((JTabbedPane) c);
  }
  
  public MyTabbedPaneUI(JTabbedPane tabbedPane) {
    // FONT: BOLD
    tabbedPane.setFont(tabbedPane.getFont().deriveFont(Font.BOLD));
  
    // FONT: WHITE
    tabbedPane.setForeground(Color.WHITE);
  
    // TAB BACKGROUND: BLUE (overrided to gray by paintTabBackground)
    //                 the background must be set to blue for the line
    //                 below the tabs to be blue, else it is the same
    //                 color as the tab background (would be gray) 
    tabbedPane.setBackground(SELECTED_TAB_COLOR);
  }
  
  // overrided to add more space each side of the tab title and spacing between tabs.
  @Override
  protected void installDefaults() {
    super.installDefaults();
  
    // changed to add more space each side of the tab title.
//    tabInsets.left = tabInsets.left + ADDED_TAB_WIDTH;
//    tabInsets.right = tabInsets.right + ADDED_TAB_WIDTH;
//    tabInsets.top = tabInsets.top + ADDED_TAB_HEIGTH;
//    tabInsets.bottom = tabInsets.bottom + ADDED_TAB_HEIGTH;
  
    // changed to define the spacing between tabs.
    selectedTabPadInsets.left = SPACE_BETWEEN_TAB;
    selectedTabPadInsets.right = SPACE_BETWEEN_TAB;
  }
  
  // overrided to paint the selected tab with a different color.
  @Override
  protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y,
                                    int w, int h, boolean isSelected) {
    // tabpane background color is not used to paint the unselected tabs
    // because we wants a different color for the background of the tabs
    // and the background of the tabpane (the line just below the tabs)
    g.setColor((!isSelected) ? TAB_COLOR : SELECTED_TAB_COLOR);
    switch (tabPlacement) {
    case LEFT:
      g.fillRect(x + 1, y + 1, w - 1, h - 3);
      break;
    case RIGHT:
      g.fillRect(x, y + 1, w - 2, h - 3);
      break;
    case BOTTOM:
      g.fillRect(x + 1, y, w - 3, h - 1);
      break;
    case TOP:
    default:
      g.fillRect(x + 1, y + 1, w - 3, h - 1);
    }
  }
  
  // overrided to add spaces between tabs.
  @Override
  protected LayoutManager createLayoutManager() {
    if (tabPane.getTabLayoutPolicy() == JTabbedPane.SCROLL_TAB_LAYOUT) {
      return super.createLayoutManager();
  
    } else { /* WRAP_TAB_LAYOUT */
      return new TabbedPaneLayout() {
  
        @Override
        protected void padSelectedTab(int tabPlacement, int selectedIndex) {
          // don't pad only the selected tab, but all the tabs, to space them.
          for (int i = 0; i < rects.length; i++) {
              Rectangle selRect = rects[i];
              Insets padInsets = getSelectedTabPadInsets(tabPlacement);
            selRect.x += padInsets.left;
            selRect.width -= (padInsets.left + padInsets.right);
            selRect.y -= padInsets.top;
            selRect.height += (padInsets.top + padInsets.bottom);
          }
        }
      };
    }
  }
}