import requests  # http://docs.python-requests.org/en/master/api/
import unittest
import json
from requests.status_codes import codes

class SampleTestCase(unittest.TestCase):

    _HOST = "localhost"
    _PORT = "1234"

    def setUp(self):
        pass

    def tearDown(self):
        pass

    @staticmethod
    def test_basic():
        headers = {'Content-type': 'application/json', 'Accept': 'application/json'}
        jsonMessageBody =  json.dumps({"name": "Sue"})
        result = requests.request("POST",
                                  "http://" + SampleTestCase._HOST + ":" + SampleTestCase._PORT + "/v1/commands/sample_app/return_json",
                                  data=jsonMessageBody,
                                  params=None,
                                  headers=headers)
        assert result.status_code == codes.OK , "Status code was expected to be 200, but was " + result.status_code
        name = result.json()["data"]["results"][0]["name"]
        assert name == "Sue";

        list = result.json()["data"]["results"][0]["values"]
        assert list[0] == 1
        assert list[1] == 2
        assert list[2] == 3
        assert len(list) == 3

def suite():
    version_suite = unittest.makeSuite(SampleTestCase, "test")
    return unittest.TestSuite((version_suite))


if __name__ == "__main__":
    unittest.TextTestRunner().run(suite())
