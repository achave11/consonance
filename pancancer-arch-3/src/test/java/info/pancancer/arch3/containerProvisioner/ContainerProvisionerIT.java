/*
 * Copyright (C) 2015 CancerCollaboratory
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package info.pancancer.arch3.containerProvisioner;

import java.io.File;
import joptsimple.OptionException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 *
 * @author dyuen
 */
public class ContainerProvisionerIT {

    /**
     * Test of main method, of class ContainerProvisioner.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = OptionException.class)
    public void testHelpMessage() throws Exception {
        ContainerProvisioner.main(new String[] { "--help" });
    }

    /**
     * Test of main method, of class ContainerProvisioner.
     *
     * @throws java.lang.Exception
     */
    // @Test
    public void testNormalOperation() throws Exception {
        // need to create a vm order first, otherwise will hang

        File file = FileUtils.getFile("src", "test", "resources", "config.json");
        ContainerProvisioner.main(new String[] { "--config", file.getAbsolutePath() });
    }

}
