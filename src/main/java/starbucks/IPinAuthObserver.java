/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Pin Auth Observer Interface */
public interface IPinAuthObserver
{
    /** Auth Event */
    void authEvent() ;
    /** No Auth Event */
    void notAuthEvent();
}
