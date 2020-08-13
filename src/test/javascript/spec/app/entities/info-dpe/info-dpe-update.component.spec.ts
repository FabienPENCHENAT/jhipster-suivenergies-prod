import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { InfoDPEUpdateComponent } from 'app/entities/info-dpe/info-dpe-update.component';
import { InfoDPEService } from 'app/entities/info-dpe/info-dpe.service';
import { InfoDPE } from 'app/shared/model/info-dpe.model';

describe('Component Tests', () => {
  describe('InfoDPE Management Update Component', () => {
    let comp: InfoDPEUpdateComponent;
    let fixture: ComponentFixture<InfoDPEUpdateComponent>;
    let service: InfoDPEService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [InfoDPEUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InfoDPEUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InfoDPEUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InfoDPEService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InfoDPE(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new InfoDPE();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
