import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { InfoDPEService } from 'app/entities/info-dpe/info-dpe.service';
import { IInfoDPE, InfoDPE } from 'app/shared/model/info-dpe.model';

describe('Service Tests', () => {
  describe('InfoDPE Service', () => {
    let injector: TestBed;
    let service: InfoDPEService;
    let httpMock: HttpTestingController;
    let elemDefault: IInfoDPE;
    let expectedResult: IInfoDPE | IInfoDPE[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InfoDPEService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new InfoDPE(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        currentDate,
        'AAAAAAA',
        'image/png',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateDPE: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a InfoDPE', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateDPE: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDPE: currentDate,
          },
          returnedFromService
        );

        service.create(new InfoDPE()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a InfoDPE', () => {
        const returnedFromService = Object.assign(
          {
            numero: 'BBBBBB',
            adresse: 'BBBBBB',
            typeBatiment: 'BBBBBB',
            anneeConstruction: 1,
            surfaceHabitable: 1,
            energieChauffage: 'BBBBBB',
            energieEauChaude: 'BBBBBB',
            energiePhotovoltaique: 1,
            dateDPE: currentDate.format(DATE_FORMAT),
            classeConsoEnergie: 'BBBBBB',
            dpeJson: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDPE: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of InfoDPE', () => {
        const returnedFromService = Object.assign(
          {
            numero: 'BBBBBB',
            adresse: 'BBBBBB',
            typeBatiment: 'BBBBBB',
            anneeConstruction: 1,
            surfaceHabitable: 1,
            energieChauffage: 'BBBBBB',
            energieEauChaude: 'BBBBBB',
            energiePhotovoltaique: 1,
            dateDPE: currentDate.format(DATE_FORMAT),
            classeConsoEnergie: 'BBBBBB',
            dpeJson: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDPE: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a InfoDPE', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
