/**
 *     Copyright (C) 2013 Contributors
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
 */
package wattsup.jsdk.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import wattsup.jsdk.core.data.WattsUpPacket;
import wattsup.jsdk.remote.data.CommandType;
import wattsup.jsdk.remote.data.Response;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public final class Main
{
    /**
     * Constructor.
     */
    private Main()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param args
     *            The console arguments.
     * @throws IOException
     *             If an I/O error occurs.
     * @throws DataFormatException 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void main(String[] args) throws IOException, DataFormatException
    {
        ClientCommand client = new ClientCommand();
        JCommander commander = new JCommander(client);

        OutputStream out = System.out;

        try
        {
            commander.parse(args);
            Response response = client.execute();
            
            if (client.getOutputFile() != null)
            {
                client.getOutputFile().createNewFile();
                out = new FileOutputStream(client.getOutputFile());
                
            }
            
            if (CommandType.START.equals(client.getCommand()))
            {
                System.out.println(response.getId());
            }
            else
            {
                Collection<WattsUpPacket> data = response.getData() instanceof Map ? ((Map) response.getData()).values()
                        : response.getData() instanceof Collection ? (Collection<WattsUpPacket>) response.getData() : Collections.emptyList();

                data = new ArrayList<>(data);
                Collections.sort((List<WattsUpPacket>) data);

                for (WattsUpPacket packet : data)
                {
                    client.getOutputFormat().getSerialize().serialize(out, packet);
                }
            }
        }
        catch (ParameterException exception)
        {
            System.err.println(exception.getMessage());
            commander.usage();
        }
        finally
        {
            out.close();
        }
    }
}
