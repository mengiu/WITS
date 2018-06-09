package org.services.layer.wits.logic;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNKitTest {
	public static void main(String[] args) {
		DAVRepositoryFactory.setup();
		String url = "https://webgate.ec.europa.eu/CITnet/svn/WITS2/WITS/trunk/";
		// GIT Repository https://webgate.ec.europa.eu/CITnet/git/WITS2
		String name = "mennegi";
		String password = "$Mengiu1";

		SVNRepository repository = null;
		try {
			repository = SVNRepositoryFactory.create( SVNURL.parseURIDecoded( url ) );
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( name , password );
			repository.setAuthenticationManager( authManager );
			System.out.println( "Repository Root: " + repository.getRepositoryRoot( true ) );
			System.out.println(  "Repository UUID: " + repository.getRepositoryUUID( true ) );
			listEntries( repository , "" );
			SVNNodeKind nodeKind = repository.checkPath( "" ,  -1 );
			if ( nodeKind == SVNNodeKind.NONE ) {
				System.err.println( "There is no entry at '" + url + "'." );
				System.exit( 1 );
			} else if ( nodeKind == SVNNodeKind.FILE ) {
				System.err.println( "The entry at '" + url + "' is a file while a directory was expected." );
				System.exit( 1 );
			}

			long latestRevision = repository.getLatestRevision( );
			System.out.println( "Repository latest revision: " + latestRevision );
		} catch ( SVNException svne ) {
			//handle exception
			int i = 0;
		}
	}
    public static void listEntries( SVNRepository repository, String path ) throws SVNException {
        Collection entries = repository.getDir( path, -1 , null , (Collection) null );
        Iterator iterator = entries.iterator( );
        while ( iterator.hasNext( ) ) {
            SVNDirEntry entry = ( SVNDirEntry ) iterator.next( );
            System.out.println( "/" + (path.equals( "" ) ? "" : path + "/" ) + entry.getName( ) + 
                               " ( author: '" + entry.getAuthor( ) + "'; revision: " + entry.getRevision( ) + 
                               "; date: " + entry.getDate( ) + ")" );
            if ( entry.getKind() == SVNNodeKind.DIR ) {
                listEntries( repository, ( path.equals( "" ) ) ? entry.getName( ) : path + "/" + entry.getName( ) );
            }
        }
    }
	
}
