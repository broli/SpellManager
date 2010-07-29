/* generated javascript */
var skin = 'sledged';
var stylepath = '/w/skins';

/* MediaWiki:Common.js */
if ( !Array.prototype.indexOf )
{
    Array.prototype.indexOf = function( elt /*, from*/ )
    {
        var len = this.length;
        var from = Number( arguments[1] ) || 0;
        from = from < 0 ? Math.ceil( from ) : Math.floor( from );

        if ( from < 0 )
            from += len;

        for ( ; from < len; from++ )
        {
            if ( from in this && this[from] === elt )

            return from;
        }

        return -1;
    };
}

function Map()
{
    // private members
    var mKeyArray = new Array(); // Keys
    var mValueArray = new Array(); // Values

    // methods
    this.clear = function()
    {
        mKeyArray.length = 0;
        mValueArray.length = 0;
    };

    this.containsKey = function( key )
    {
        return mKeyArray.indexOf( key ) == -1 ? false : true;
    };

    this.containsValue = function( value )
    {
        return mValueArray.indexOf( value ) == -1 ? false : true;
    };

    this.entrySet = function()
    {
        set = new Array();

        for ( i = 0; i < mKeyArray.length; i++ )
        {
            entry = new Object();
            entry.key = mKeyArray[i];
            entry.value = valArray[i];
            set.push( entry );
        }

        return set;
    };

    this.get = function( key )
    {
        var index = mKeyArray.indexOf( key );

        return index == -1 ? null : mValueArray[index];
    };

    this.isEmpty = function()
    {
        return mKeyArray.length == 0 ? true : false;
    }

    this.keySet = function()
    {
        // return a copy of the key set array
        return mKeyArray.slice(0);
    };

    this.put = function( key, val )
    {
        var index = mKeyArray.indexOf( key );

        if ( index == -1 )
        {
            mKeyArray.push( key );
            mValueArray.push( val );
            return null;
        }

        var old = mValueArray[index];
        mValueArray[index] = val;

        return old;
    };

    this.putAll = function( map )
    {
        if ( !Map.prototype.isPrototypeOf( map ) )
            throw new TypeError( "Argument is not of class 'Map'." );

        var keys = map.keySet();

        for ( i = 0; i < keys.length; i++ )
        {
            key = keys[i];
            this.put( key, map.get( key ) );
        }
    };

    this.remove = function( key )
    {
        var index = mKeyArray.indexOf( key );

        if ( index == -1 )
            return null;

        var old = mValueArray[index];
        mKeyArray = mKeyArray.splice( index, 1 );
        mValueArray = mValueArray.splice( index, 1 );
        return old;
    };

    this.size = function()
    {
        return mKeyArray.length;
    };

    this.toString = function()
    {
        var result = "{";

        for( var i = 0; i < mKeyArray.length; i++ )
        {
            if ( i != 0 )
                result += ", ";

            result += mKeyArray[i].valueOf() + "=" + mValueArray[i].valueOf();
        }

        result = "}";

        return result;
    };   // returns a string with all keys and values in map.

    this.values = function()
    {
        // return a copy of the value set array
        return mValueArray.slice(0);
    };
}

var gTableMap;
var gCollapseCaption = "hide";
var gExpandCaption = "show";

function CollapsibleTable( table, span )
{
    this.domTable = table;
    this.domTextSpan = span;
    this.rowDisplayMap = new Map();
    this.collapsed = false;
}

CollapsibleTable.prototype.COLLAPSE_TEXT = "hide";
CollapsibleTable.prototype.EXPAND_TEXT = "show";

function initCollapsibleTables()
{
    // get all the table elements
    var tables = document.getElementsByTagName( "table" );
    gTableMap = new Map();

    // sort out the table elements with the class name "collapsible"
    for ( i = 0; i < tables.length; i++ )
    {
        var table = tables[i];

        // multiple class names are separated by white space
        var classes = table.className.split( /\s+/ );

        // "collapsible"? Are you there?
        if ( classes.indexOf( "collapsible" ) != -1 )
        {
            // create a collapse button for each
            // get the existing caption element or create a new one
            var caption = table.createCaption();
            // create span element...
            var spanButton = document.createElement( "span" );
            // create span element...
            var spanText = document.createElement( "span" );
            // ... of the class showHideButton
            spanButton.className = "showHideButton";
            // create onclick attribute with a value of
            // "tableCollapseExpand(this);"
            spanButton.setAttribute( "onclick", "tableCollapseExpand(this);" );
            // create text node with a value of "hide"
            var text = document.createTextNode( gCollapseCaption );
            // nest the text in the span tag
            spanText.appendChild( text );
            spanButton.appendChild( document.createTextNode( "[" ) );
            spanButton.appendChild( spanText );
            spanButton.appendChild( document.createTextNode( "]" ) );
            // append span element to caption element
            caption.insertBefore( spanButton, caption.firstChild );
            // map the span element to the table
            gTableMap.put( spanButton,
                new CollapsibleTable( table, spanText ) );

            // see if it should initially start collapsed
            if ( classes.indexOf( "hidden" ) != -1 || classes.indexOf( "collapsed" ) != -1 )
            {
                tableCollapseExpand( spanButton );
            }
        }
    }
}

/*

== Tests ==

=== class="collapsible" ===

{| class="d20{{#vardefine: odd|0}} collapsible"
|+ style="width: 100%" | Table: Caption
|-
! What
|-
| I feel happy!
|}

=== class="collapsible collapsed" ===

{| class="d20{{#vardefine: odd|0}} collapsible collapsed"
|+ style="width: 100%" | Table: Caption
|-
! What
|-
| I feel happy!
|}

=== class="collapsible hidden" ===

{| class="d20{{#vardefine: odd|0}} collapsible hidden"
|+ style="width: 100%" | Table: Caption
|-
! What
|-
| I feel happy!
|}

=== class="collapsible collapsed hidden" ===

{| class="d20{{#vardefine: odd|0}} collapsible collapsed hidden"
|+ style="width: 100%" | Table: Caption
|-
! What
|-
| I feel happy!
|}

*/

function tableCollapseExpand( elem )
{
    // get the table mapped to this HTML element
    var table = gTableMap.get( elem );
    var domTable = table.domTable;

    if ( table.collapsed )
    {
        for ( var i = 0; i < domTable.rows.length; i++ )
        {
            var row = domTable.rows[i];

            // if the display value was changed while
            // it was hidden, preserve tbe new value
            if ( row.style.display == "none" )
                row.style.display = table.rowDisplayMap.get( row );
        }

        table.collapsed = false;
        table.domTextSpan.firstChild.data = table.COLLAPSE_TEXT;
        return;
    }

    for ( var i = 0; i < domTable.rows.length; i++ )
    {
        var row = domTable.rows[i];
        table.rowDisplayMap.put( row, row.style.display );
        row.style.display = "none";
    }

    table.collapsed = true;
    table.domTextSpan.firstChild.data = table.EXPAND_TEXT;
}

addOnloadHook( initCollapsibleTables );

/** Dynamic Navigation Bars (experimental) *************************************
 *
 *  Description: See [[Wikipedia:NavFrame]].
 *  Maintainers: UNMAINTAINED
 */
 
// set up the words in your language
var NavigationBarHide = '[' + collapseCaption + ']';
var NavigationBarShow = '[' + expandCaption + ']';
 
// shows and hides content and picture (if available) of navigation bars
// Parameters:
//     indexNavigationBar: the index of navigation bar to be toggled
function toggleNavigationBar(indexNavigationBar)
{
    var NavToggle = document.getElementById("NavToggle" + indexNavigationBar);
    var NavFrame = document.getElementById("NavFrame" + indexNavigationBar);
 
    if (!NavFrame || !NavToggle) {
        return false;
    }
 
    // if shown now
    if (NavToggle.firstChild.data == NavigationBarHide) {
        for (var NavChild = NavFrame.firstChild; NavChild != null; NavChild = NavChild.nextSibling) {
            if ( hasClass( NavChild, 'NavPic' ) ) {
                NavChild.style.display = 'none';
            }
            if ( hasClass( NavChild, 'NavContent') ) {
                NavChild.style.display = 'none';
            }
        }
    NavToggle.firstChild.data = NavigationBarShow;
 
    // if hidden now
    } else if (NavToggle.firstChild.data == NavigationBarShow) {
        for (var NavChild = NavFrame.firstChild; NavChild != null; NavChild = NavChild.nextSibling) {
            if (hasClass(NavChild, 'NavPic')) {
                NavChild.style.display = 'block';
            }
            if (hasClass(NavChild, 'NavContent')) {
                NavChild.style.display = 'block';
            }
        }
        NavToggle.firstChild.data = NavigationBarHide;
    }
}
 
// adds show/hide-button to navigation bars
function createNavigationBarToggleButton()
{
    var indexNavigationBar = 0;
    // iterate over all < div >-elements 
    var divs = document.getElementsByTagName("div");
    for (var i = 0; NavFrame = divs[i]; i++) {
        // if found a navigation bar
        if (hasClass(NavFrame, "NavFrame")) {
 
            indexNavigationBar++;
            var NavToggle = document.createElement("a");
            NavToggle.className = 'NavToggle';
            NavToggle.setAttribute('id', 'NavToggle' + indexNavigationBar);
            NavToggle.setAttribute('href', 'javascript:toggleNavigationBar(' + indexNavigationBar + ');');
 
            var isCollapsed = hasClass( NavFrame, "collapsed" );
            /*
             * Check if any children are already hidden.  This loop is here for backwards compatibility:
             * the old way of making NavFrames start out collapsed was to manually add style="display:none"
             * to all the NavPic/NavContent elements.  Since this was bad for accessibility (no way to make
             * the content visible without JavaScript support), the new recommended way is to add the class
             * "collapsed" to the NavFrame itself, just like with collapsible tables.
             */
            for (var NavChild = NavFrame.firstChild; NavChild != null && !isCollapsed; NavChild = NavChild.nextSibling) {
                if ( hasClass( NavChild, 'NavPic' ) || hasClass( NavChild, 'NavContent' ) ) {
                    if ( NavChild.style.display == 'none' ) {
                        isCollapsed = true;
                    }
                }
            }
            if (isCollapsed) {
                for (var NavChild = NavFrame.firstChild; NavChild != null; NavChild = NavChild.nextSibling) {
                    if ( hasClass( NavChild, 'NavPic' ) || hasClass( NavChild, 'NavContent' ) ) {
                        NavChild.style.display = 'none';
                    }
                }
            }
            var NavToggleText = document.createTextNode(isCollapsed ? NavigationBarShow : NavigationBarHide);
            NavToggle.appendChild(NavToggleText);
 
            // Find the NavHead and attach the toggle link (Must be this complicated because Moz's firstChild handling is borked)
            for(var j=0; j < NavFrame.childNodes.length; j++) {
                if (hasClass(NavFrame.childNodes[j], "NavHead")) {
                    NavFrame.childNodes[j].appendChild(NavToggle);
                }
            }
            NavFrame.setAttribute('id', 'NavFrame' + indexNavigationBar);
        }
    }
}
 
addOnloadHook( createNavigationBarToggleButton );

/* MediaWiki:Sledged.js */
/* tooltips and access keys */
ta = new Object();
ta['pt-userpage'] = new Array('.','My user page');
ta['pt-anonuserpage'] = new Array('.','The user page for the ip you\'re editing as');
ta['pt-mytalk'] = new Array('n','My talk page');
ta['pt-anontalk'] = new Array('n','Discussion about edits from this ip address');
ta['pt-preferences'] = new Array('','My preferences');
ta['pt-watchlist'] = new Array('l','The list of pages you\'re monitoring for changes.');
ta['pt-mycontris'] = new Array('y','List of my contributions');
ta['pt-login'] = new Array('o','You are encouraged to log in, it is not mandatory however.');
ta['pt-anonlogin'] = new Array('o','You are encouraged to log in, it is not mandatory however.');
ta['pt-logout'] = new Array('o','Log out');
ta['ca-talk'] = new Array('t','Discussion about the content page');
ta['ca-edit'] = new Array('e','You can edit this page. Please use the preview button before saving.');
ta['ca-addsection'] = new Array('+','Add a comment to this discussion.');
ta['ca-viewsource'] = new Array('e','This page is protected. You can view its source.');
ta['ca-history'] = new Array('h','Past versions of this page.');
ta['ca-protect'] = new Array('=','Protect this page');
ta['ca-delete'] = new Array('d','Delete this page');
ta['ca-undelete'] = new Array('d','Restore the edits done to this page before it was deleted');
ta['ca-move'] = new Array('m','Move this page');
ta['ca-watch'] = new Array('w','Add this page to your watchlist');
ta['ca-unwatch'] = new Array('w','Remove this page from your watchlist');
ta['search'] = new Array('f','Search this wiki');
ta['p-logo'] = new Array('','Main Page');
ta['n-mainpage'] = new Array('z','Visit the Main Page');
ta['n-portal'] = new Array('','About the project, what you can do, where to find things');
ta['n-currentevents'] = new Array('','Find background information on current events');
ta['n-recentchanges'] = new Array('r','The list of recent changes in the wiki.');
ta['n-randompage'] = new Array('x','Load a random page');
ta['n-help'] = new Array('','The place to find out.');
ta['n-sitesupport'] = new Array('','Support us');
ta['t-whatlinkshere'] = new Array('j','List of all wiki pages that link here');
ta['t-recentchangeslinked'] = new Array('k','Recent changes in pages linked from this page');
ta['feed-rss'] = new Array('','RSS feed for this page');
ta['feed-atom'] = new Array('','Atom feed for this page');
ta['t-contributions'] = new Array('','View the list of contributions of this user');
ta['t-emailuser'] = new Array('','Send a mail to this user');
ta['t-upload'] = new Array('u','Upload images or media files');
ta['t-specialpages'] = new Array('q','List of all special pages');
ta['ca-nstab-main'] = new Array('c','View the content page');
ta['ca-nstab-user'] = new Array('c','View the user page');
ta['ca-nstab-media'] = new Array('c','View the media page');
ta['ca-nstab-special'] = new Array('','This is a special page, you can\'t edit the page itself.');
ta['ca-nstab-wp'] = new Array('a','View the project page');
ta['ca-nstab-image'] = new Array('c','View the image page');
ta['ca-nstab-mediawiki'] = new Array('c','View the system message');
ta['ca-nstab-template'] = new Array('c','View the template');
ta['ca-nstab-help'] = new Array('c','View the help page');
ta['ca-nstab-category'] = new Array('c','View the category page');



// addPurge
addOnloadHook( function (){
    var x = document.getElementById('ca-history');
    if(!x) return;
    if(x.children) x = x.children[0].href;
    else x = x.childNodes[0].href;
    addLink("p-cactions", x.replace(/=history/, "=purge"), 'purge', 'ca-purge', 'Purge the internal cache for this page', 0);
});

function addTab(url, name, id, title, key){
    var tabs = document.getElementById('p-cactions').getElementsByTagName('ul')[0];
    return addlilink(tabs, url, name, id, title, key);
}

function addLink(where, url, name, id, title, key, after){
    //* where is the id of the toolbar where the button should be added;
    //   i.e. one of "p-cactions", "p-personal", "p-navigation", or "p-tb".
    //
    //* url is the URL which will be called when the button is clicked.
    //   javascript: urls can be used to do more complex things.
    //
    //* name is what will appear as the name of the button.
    //
    //* id is the id of the button; it's best to define one.  
    //   Use a prefix to make sure its unique. Optional.
    //
    //* title is the tooltip title that gives a longer description 
    //   of the button; if you define a accesskey, mention it here. Optional.
    //
    //* key is the char you want for the accesskey. Optional.
    //
    //* after is the id of the button you want to follow this one. Optional.
    //
    var na = document.createElement('a');
    na.href = url;
    na.appendChild(document.createTextNode(name));
    var li = document.createElement('li');
    if(id) li.id = id;
    li.appendChild(na);
    var tabs = document.getElementById(where).getElementsByTagName('ul')[0];
    if(after) {
        tabs.insertBefore(li,document.getElementById(after));
    } else {
        tabs.appendChild(li);
    }
    if(id) {
        if(key && title) { ta[id] = [key, title]; }
        else if(key) { ta[id] = [key, '']; }
        else if(title) { ta[id] = ['', title];} 
    }
    // re-render the title and accesskeys from existing code in wikibits.js
    akeytt();
    return li;
}
    
//