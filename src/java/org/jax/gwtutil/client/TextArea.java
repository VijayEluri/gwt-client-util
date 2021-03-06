/*
 * Copyright (c) 2010 The Jackson Laboratory
 * 
 * This is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jax.gwtutil.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.i18n.client.BidiUtils;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

/*
 * NOTES:
 * The only reason I'm not using 
 * This is code lifted from the GWT code base. It's currently only in GWT's SVN,
 * but we should be able to delete this class once Google releases it.
 */
/**
 * A text box that allows multiple lines of text to be entered.
 * 
 * <p>
 * <img class='gallery' src='TextArea.png'/>
 * </p>
 * 
 * <h3>CSS Style Rules</h3>
 * <ul class='css'>
 * <li>.gwt-TextArea { primary style }</li>
 * <li>.gwt-TextArea-readonly { dependent style set when the text area is read-only }</li>
 * </ul>
 * 
 * <p>
 * <h3>Example</h3> {@example com.google.gwt.examples.TextBoxExample}
 * </p>
 */
@SuppressWarnings("all")
public class TextArea extends TextBoxBase implements HasDirection {

  /**
   * Creates a TextArea widget that wraps an existing &lt;textarea&gt;
   * element.
   * 
   * This element must already be attached to the document. If the element is
   * removed from the document, you must call
   * {@link RootPanel#detachNow(Widget)}.
   * 
   * @param element the element to be wrapped
   */
  public static TextArea wrap(Element element) {
    // Assert that the element is attached.
    assert Document.get().getBody().isOrHasChild(element);

    TextArea textArea = new TextArea(element);

    // Mark it attached and remember it for cleanup.
    textArea.onAttach();
    RootPanel.detachOnWindowClose(textArea);

    return textArea;
  }

  /**
   * Creates an empty text area.
   */
  public TextArea() {
    super(Document.get().createTextAreaElement());
    setStyleName("gwt-TextArea");
  }

  /**
   * This constructor may be used by subclasses to explicitly use an existing
   * element. This element must be a &lt;textarea&gt; element.
   * 
   * @param element the element to be used
   */
  protected TextArea(Element element) {
    super(element.<Element>cast());
    TextAreaElement.as(element);
  }

  /**
   * Gets the requested width of the text box (this is not an exact value, as
   * not all characters are created equal).
   * 
   * @return the requested width, in characters
   */
  public int getCharacterWidth() {
    return getTextAreaElement().getCols();
  }

  @Override
  public int getCursorPos() {
    return getImpl().getTextAreaCursorPos(getElement());
  }

  public Direction getDirection() {
    return BidiUtils.getDirectionOnElement(getElement());
  }

  @Override
  public int getSelectionLength() {
    return getImpl().getSelectionLength(getElement());
  }

  /**
   * Gets the number of text lines that are visible.
   * 
   * @return the number of visible lines
   */
  public int getVisibleLines() {
    return getTextAreaElement().getRows();
  }

  /**
   * Sets the requested width of the text box (this is not an exact value, as
   * not all characters are created equal).
   * 
   * @param width the requested width, in characters
   */
  public void setCharacterWidth(int width) {
    getTextAreaElement().setCols(width);
  }

  public void setDirection(Direction direction) {
    BidiUtils.setDirectionOnElement(getElement(), direction);
  }

  /**
   * Sets the number of text lines that are visible.
   * 
   * @param lines the number of visible lines
   */
  public void setVisibleLines(int lines) {
    getTextAreaElement().setRows(lines);
  }

  private TextAreaElement getTextAreaElement() {
    return getElement().cast();
  }
}
