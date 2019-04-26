<?php

   /**
    *
    * submit contact form or appointment form
    * to your email address
    * 
    * @author: Martanian <support@martanian.com>        
    * 
    */

    # put your email address here
    define( 'OWNER_EMAIL', 'your-email@example.com' );
    
    # put your website "do not reply" email here
    define( 'DONOTREPLY_EMAIL', 'do-not-reply@example.com' );
    
    # put your name here
    define( 'OWNER_NAME', 'Insurance Template' );

   /**
    *
    * what we need to do?
    * 
    */
    
    switch( $_POST['send'] ) {
    
       /**
        *
        * sending contact form
        * 
        */

        case 'contact-form':                                            
    
            # put the email title here
            $title = $_POST['clientMessageTitle'];
        
            # email headers
            $headers = "MIME-Version: 1.0\n".
                       "Content-type: text/html; charset=utf-8\n".
                       "Content-Transfer-Encoding: 8bit\n".
                       "From: ". $_POST['clientName'] ." <". $_POST['clientEmail'] .">\n".
                       "Reply-to: ". $_POST['clientName'] ." <". $_POST['clientEmail'] .">\n".
                       "Date: ". date( "r" ). "\n";
        
            # appointment values
            $values = $_POST['values'];

            # create rows with values from appointment form
            $rows = '';
            for( $i = 0; $i < count( $values ); $i++ ) {
            
                $rows .= '<tr>
                              
                              <td style="width: 200px; font-weight: bold; border: 1px solid #eee; padding: 10px;">'. $values[$i]['name'] .'</td>
                              <td style="border: 1px solid #eee; padding: 10px;">'. $values[$i]['value'] .'</td>
                          
                          </tr>';
            }

            # email content
            $content = '<table style="width: 600px; font-size: 11px; border-collapse: collapse;">'. $rows .'</table>';
            
            # sending an email
            $result = mail(
                OWNER_EMAIL,
                "=?UTF-8?B?". base64_encode( $title ) ."?=",
                $content,
                $headers
            );
            
            # if the email wasn't send
            if( $result == false ) {
            
                # second version of email
                mail(
                    OWNER_EMAIL,
                    "=?UTF-8?B?". base64_encode( EMAIL_TITLE ) ."?=",
                    $content
                );
            }

        break;

       /**
        *
        * sending insurance quote
        * 
        */
        
        case 'quote-form':   

            # put the email title here
            $title = 'New Insurance Quote from your website';
        
            # email headers
            $headers = "MIME-Version: 1.0\n".
                       "Content-type: text/html; charset=utf-8\n".
                       "Content-Transfer-Encoding: 8bit\n".
                       "From: ". $_POST['clientName'] ." <". $_POST['clientEmail'] .">\n".
                       "Reply-to: ". $_POST['clientName'] ." <". $_POST['clientEmail'] .">\n".
                       "Date: ". date( "r" ). "\n";
        
            # appointment values
            $values = $_POST['values'];

            # create rows with values from appointment form
            $rows = '';
            for( $i = 0; $i < count( $values ); $i++ ) {
            
                $rows .= '<tr>
                              
                              <td style="width: 200px; font-weight: bold; border: 1px solid #eee; padding: 10px;">'. $values[$i]['name'] .'</td>
                              <td style="border: 1px solid #eee; padding: 10px;">'. $values[$i]['value'] .'</td>
                          
                          </tr>';
            }

            # email content
            $content = '<table style="width: 600px; font-size: 11px; border-collapse: collapse;">'. $rows .'</table>';
            
            # sending an email
            $result = mail(
                OWNER_EMAIL,
                "=?UTF-8?B?". base64_encode( $title ) ."?=",
                $content,
                $headers
            );
            
            # if the email wasn't send
            if( $result == false ) {
            
                # second version of email
                mail(
                    OWNER_EMAIL,
                    "=?UTF-8?B?". base64_encode( EMAIL_TITLE ) ."?=",
                    $content
                );
            }
        
        break;
        
       /**
        *
        * phone call request
        *
        */                  
        
        case 'phone-form':
        
            # put the email title here
            $title = 'New Phone Call Request from your website';
        
            # email headers
            $headers = "MIME-Version: 1.0\n".
                       "Content-type: text/html; charset=utf-8\n".
                       "Content-Transfer-Encoding: 8bit\n".
                       "From: ". OWNER_NAME ." <". DONOTREPLY_EMAIL .">\n".
                       "Date: ". date( "r" ). "\n";

            # email content
            $content = 'New Phone Call Request from your website: <strong>'. $_POST['phoneNumber'] .'</strong>';
            
            # sending an email
            $result = mail(
                OWNER_EMAIL,
                "=?UTF-8?B?". base64_encode( $title ) ."?=",
                $content,
                $headers
            );
            
            # if the email wasn't send
            if( $result == false ) {
            
                # second version of email
                mail(
                    OWNER_EMAIL,
                    "=?UTF-8?B?". base64_encode( EMAIL_TITLE ) ."?=",
                    $content
                );
            }
            
        break;               
        
       /**
        *
        * end of options.
        * 
        */                                
        
    }                                

   /**
    *
    * end of file.
    * 
    */                                   

?>