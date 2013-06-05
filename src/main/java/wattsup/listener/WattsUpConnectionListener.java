/**
 *     WattsUp-J is a Java application to interact with the Watts up? power meter.
 *     Copyright (C) 2013  Contributors
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 *     Contributors:
 *         Alessandro Ferreira Leite - the initial implementation.
 */
package wattsup.listener;

import wattsup.event.WattsUpConnectedEvent;

public interface WattsUpConnectionListener extends WattsUpListener
{
    /**
     * Called after a connection with the meter has been established.
     * 
     * @param event
     *            The {@link WattsUpConnectedEvent} with the data about the connected power meter.
     */
    void onConnected(WattsUpConnectedEvent event);
}
