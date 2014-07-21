package com.google.bitcoin.core;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Pyramidscoin
 * Date: 7/11/14
 * Time: 2:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "PyramidsCoin";
    public static final String coinTicker = "PYRA";
    public static final String coinURIScheme = "Pyramidscoin";
    public static final String cryptsyMarketId = "155";
    public static final String cryptsyMarketCurrency = "BTC";
    public static final String PATTERN_PRIVATE_KEY_START = "U";

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;


    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://explorer.pyramidscoin.com:39/";    // OPX
    public static final String BLOCKEXPLORER_ADDRESS_PATH = "address/";             // OPX
    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "tx/";              // OPX
    public static final String BLOCKEXPLORER_BLOCK_PATH = "block/";                 // OPX
    public static final String BLOCKEXPLORER_BASE_URL_TEST = BLOCKEXPLORER_BASE_URL_PROD;

    public static final String DONATION_ADDRESS = "PbeoZkg8ELA78Cra11uXVUsr1jyaSbfhFE";  //Pyramidscoin donation address

    enum CoinHash {
        SHA256,
        scrypt,
        x11
    };
    public static final CoinHash coinPOWHash = CoinHash.x11;

    public static boolean checkpointFileSupport = true;

    public static final int TARGET_TIMESPAN = (int)(24 * 60 * 60);  // 1 day
    public static final int TARGET_SPACING = (int)(30);  // 2 blocks per Minute
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  // 2880 blocks

    public static final int getInterval(int height, boolean testNet) {
            return INTERVAL;      //108
    }
    public static final int getIntervalCheckpoints() {
            return INTERVAL;

    }
    public static final int getTargetTimespan(int height, boolean testNet) {
            return TARGET_TIMESPAN;    //72 min
    }

    public static int spendableCoinbaseDepth = 100; //main.h: static const int COINBASE_MATURITY
    public static final BigInteger MAX_MONEY = BigInteger.valueOf(95000000).multiply(Utils.COIN);                 //main.h:  MAX_MONEY
    //public static final String MAX_MONEY_STRING = "200000000";     //main.h:  MAX_MONEY

    public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger.valueOf(100000);   // MIN_TX_FEE
    public static final BigInteger DUST_LIMIT = BigInteger.valueOf(100000); //main.h CTransaction::GetMinFee        0.001 coins

    public static final int PROTOCOL_VERSION = 70002;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 70002;        //version.h MIN_PROTO_VERSION

    public static final int BLOCK_CURRENTVERSION = 1;   //CBlock::CURRENT_VERSION
    public static final int MAX_BLOCK_SIZE = 1 * 1000 * 1000;


    public static final boolean supportsBloomFiltering = true; //Requires PROTOCOL_VERSION 70000 in the client

    public static final int Port    = 33994;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 13994;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 56;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 5;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS
    public static final boolean allowBitcoinPrivateKey = false; //for backward compatibility with previous version of digitalcoin
    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long PacketMagic = 0xf6c6b6d6;      //0xf6, 0xc6, 0xb6, 0xd6

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1402015700L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (155979);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "00000afb843c84fe0058b81fa0308829d2fbd9efad6936ccc289f81f5485fe1e"; //main.cpp: hashGenesisBlock
    static public int genesisBlockValue = 39;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
    static public String genesisXInBytes = "04ffff001d01040e4b68656f70732032353630204243";   //"Pyramidscoin se convertira en una de las monedas mas segura del mercado, checa nuestros avances"
    static public String genessiXOutBytes = "04f56908fa45c70af85913f328f09684f346578e291e07a062d4b6ecb31f9ec23229d8f7c98df0a3a6673d74e9bb18a9300e83a87b21e9f2123db62963b33bedef";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
            "95.215.45.126"
    };

    public static int minBroadcastConnections = 0;   //0 for default; we need more peers.

    //
    // TestNet - Pyramidscoin - not tested
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 111;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0xffcfb9d3;      //0xff, 0xcf, 0xb9, 0xd3
    public static final String testnetGenesisHash = "0000082420bc79723950f448146d0cb8c979c5c67b9e734db203532bc87dfbef";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1402018666L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (588775);                         //main.cpp: LoadBlockIndex





    //main.cpp GetBlockValue(height, fee)
    public static final BigInteger GetBlockReward(int height)
    {
        int COIN = 1;
        BigInteger nSubsidy = Utils.toNanoCoins(41, 0);
        
        if (height == 1)
        {
            nSubsidy = Utils.toNanoCoins(700000, 0);
        }
        else if (height > 100 && height <= 766666)
        {
            nSubsidy = Utils.toNanoCoins(90, 0);
        }
        else if (height > 766666 && height <= 1533333)
        {
            nSubsidy = Utils.toNanoCoins(30, 0);
        }
        else if (height > 1533333 && height < 2300000)
        {
            nSubsidy = Utils.toNanoCoins(3, 0);
        }
        else if (height == 2300000)
        {
            nSubsidy = Utils.toNanoCoins(9013, 0);
        }
        else
        {
            nSubsidy = Utils.toNanoCoins(0, 0);
        }
        return nSubsidy;
    }

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // digitalcoin: starting difficulty is 1 / 2^12

    static public String[] testnetDnsSeeds = new String[] {
          "not supported"
    };
    //from main.h: CAlert::CheckSignature
    public static final String SATOSHI_KEY = "04f56908fa45c70af85913f328f09684f346578e291e07a062d4b6ecb31f9ec23229d8f7c98df0a3a6673d74e9bb18a9300e83a87b21e9f2123db62963b33bedef";
    public static final String TESTNET_SATOSHI_KEY = "";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.pyramidscoin.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.pyramidscoin.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.pyramidscoin.unittest";

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        checkpoints.put( 0,   new Sha256Hash("00000afb843c84fe0058b81fa0308829d2fbd9efad6936ccc289f81f5485fe1e"));

    }

    //Unit Test Information
    public static final String UNITTEST_ADDRESS = "";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "";

}
