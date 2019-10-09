/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil
 *
 * This file is part of the OpenLauncherLib.

 * The OpenLauncherLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The OpenLauncherLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the OpenLauncherLib.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.theshark34.openlauncherlib.util.ramselector;

import fr.theshark34.openlauncherlib.language.api.LanguageInfo;
import fr.theshark34.openlauncherlib.language.api.LanguageManager;
import fr.theshark34.openlauncherlib.util.LogUtil;

import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * The default Option Frame
 *
 * <p>
 *     The default Option Frame for the Ram Selector
 * </p>
 *
 * @author Litarvan
 * @version 3.0.2-BETA
 * @since 3.0.0-BETA
 */
public class OptionFrame extends AbstractOptionFrame
{
    /**
     * The Label "RAM : "
     */
    private JLabel ramLabel;

    /**
     * The RAM selection combo box
     */
    private JComboBox ramBox;

    /**
     * The Option Frame
     *
     * @param selector The current Ram Selector
     */
    @SuppressWarnings("unchecked")
    public OptionFrame(RamSelector selector)
    {
        super(selector);

        this.setTitle(LogUtil.getLanguageManager().getDefaultLanguage().get(LogUtil.getIdentifier(), "options"));
        this.setResizable(false);
        this.setSize(275, 100);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        ramLabel = new JLabel(LogUtil.getLanguageManager().getDefaultLanguage().get(LogUtil.getIdentifier(), "ram") + " : ");
        ramLabel.setBounds(15, 20, 45, 25);
        this.add(ramLabel);

        ramBox = new JComboBox(RamSelector.RAM_ARRAY);
        ramBox.setBounds(65, 20, 195, 25);
        this.add(ramBox);
    }

    @Override
    public int getSelectedIndex()
    {
        return ramBox.getSelectedIndex();
    }

    @Override
    public void setSelectedIndex(int index)
    {
        ramBox.setSelectedIndex(index);
    }
}
