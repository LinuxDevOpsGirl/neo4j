/*
 * Copyright (c) 2002-2016 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
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
package org.neo4j.kernel.impl.store.format;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith( Parameterized.class )
public class StoreVersionsTest
{
    @Parameterized.Parameter( 0 )
    public String version;

    @Parameterized.Parameters( name = "{0}" )
    public static Collection<String> versions()
    {
        return Arrays.asList( StoreVersions.STANDARD_V2_0, StoreVersions.STANDARD_V2_1, StoreVersions.STANDARD_V2_2,
                StoreVersions.STANDARD_V2_3, StoreVersions.STANDARD_V3_0 );
    }

    @Test
    public void shouldBeCommunityFormat()
    {
        assertTrue( "Expected community format", StoreVersions.isCommunityStoreVersion( version ) );
    }

    @Test
    public void shouldNotBeLabeledEnterpriseFormat()
    {
        assertFalse( "Expected non-enterprise format", StoreVersions.isEnterpriseStoreVersion( version ) );
    }

    @RunWith( Parameterized.class )
    public static class EnterpriseVersions
    {
        @Parameterized.Parameter( 0 )
        public String version;

        @Parameterized.Parameters( name = "{0}" )
        public static Collection<String> versions()
        {
            return Arrays.asList( StoreVersions.HIGH_LIMIT_V3_0 );
        }

        @Test
        public void shouldBeCommunityFormat()
        {
            assertFalse( "Expected non-community format", StoreVersions.isCommunityStoreVersion( version ) );
        }

        @Test
        public void shouldNotBeLabeledEnterpriseFormat()
        {
            assertTrue( "Expected enterprise format", StoreVersions.isEnterpriseStoreVersion( version ) );
        }
    }
}
