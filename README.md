# restpg
#### To generate rsa keys:
- openssl genrsa -out rsa_private_key.pem 2048
- openssl rsa -in rsa_private_key.pem -pubout -outform DER -out rsa_public_key.der
- openssl pkcs8 -topk8 -inform PEM -outform DER -in rsa_private_key.pem -out rsa_private_key.der -nocrypt